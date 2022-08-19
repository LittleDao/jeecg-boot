
package org.jeecg.modules.demo.xxljob;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.dto.message.BusMessageDTO;
import org.jeecg.common.config.mqtoken.UserTokenContext;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.demo.alertSetting.entity.BusAlertSettings;
import org.jeecg.modules.demo.alertSetting.service.IBusAlertSettingsService;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestment;
import org.jeecg.modules.demo.investment.service.IFixedAssetsInvestmentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


/**
 * xxl-job定时任务测试
 */
@Component
@Slf4j
public class TestJobHandler {
    @Resource
    IFixedAssetsInvestmentService fixedAssetsInvestmentServiceImpl;
    @Resource
    private ISysBaseAPI sysBaseAPI;
    @Resource
    RedisUtil  redisUtil;

    /**
     * 简单任务
     *
     * 测试：无token调用feign接口
     *
     * @param params
     * @return
     */

    @XxlJob(value = "testJob")
    public ReturnT<String> demoJobHandler(String params) {
        //1.生成临时令牌Token到线程中
        UserTokenContext.setToken(getTemporaryToken());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(new Date());
        List<FixedAssetsInvestment> fixedAssetsInvestments = fixedAssetsInvestmentServiceImpl.selectUserNeedNotice(nowDate);
        fixedAssetsInvestments.stream().forEach(V->{
            //获得需要处理的用户信息
            String personInCharge = V.getPersonInCharge();
            Date projectInititationTime = V.getProjectInititationTime();
            //是否已经发送过，发送过则不需要重复发送
            final String key = "test:job:sendMessage::"+V.getId();
            if(!redisUtil.hasKey(key) && StringUtil.isNotEmpty(personInCharge)){
                //发送消息，设置为工作流消息
                BusMessageDTO busMessageDTO = new BusMessageDTO("system", personInCharge, "立项提醒", "立项时间已经到达，请及时处理！", "1", "bpm", V.getId());
                sysBaseAPI.sendBusAnnouncement( busMessageDTO);
                redisUtil.set(key,"1",60);
            }
        });
        log.info("我是 jeecg-demo 服务里的定时任务 testJob , 我执行了...............................");
        //log.info("我调用 jeecg-system 服务的字典接口：{}",sysBaseApi.queryAllDict());
        //。。。此处可以写多个feign接口调用

        //2.使用完，删除临时令牌Token
        UserTokenContext.remove();
        return ReturnT.SUCCESS;
    }

    /**
     * 预警设置
     */
    @Resource
    IBusAlertSettingsService busAlertSettingsService;
    /***
     * 年度投资计划提醒
     * @param params
     * @return
     */
    @XxlJob(value = "planJob")
    public ReturnT<String> planJob(String params) {
        //1.生成临时令牌Token到线程中
        UserTokenContext.setToken(getTemporaryToken());

        LocalDate localDate = LocalDate.now();
        final int month = localDate.getMonthValue();
        final int day = localDate.getDayOfMonth();
        final String lockKey = "test:job:sendMessage::"+month+"~"+day;
        if(!redisUtil.hasKey(lockKey)){
            redisUtil.set(lockKey,1,60*60*24);
            //获得年度投资计划配置项，向所有人发送消息
            List<BusAlertSettings> list = busAlertSettingsService.list();
            list.stream().forEach(V->{
                if(V.getAlertMonth()==month && V.getAlertDay()==day){
                    //发送消息，设置为工作流消息
                    BusMessageDTO busMessageDTO = new BusMessageDTO("system", "admin", "年度投资计划预警", "年度计划已经开通请做好年度计划！", "1", "investment", V.getId());
//                    busMessageDTO.setToAll(true);
                    sysBaseAPI.sendBusAnnouncement( busMessageDTO);
                }
            });
        }

        log.info("我是 jeecg-demo 服务里的定时任务 planJob , 我执行了...............................");
//        log.info("我调用 jeecg-system 服务的字典接口：{}",sysBaseApi.queryAllDict());
        //。。。此处可以写多个feign接口调用

        //2.使用完，删除临时令牌Token
        UserTokenContext.remove();
        return ReturnT.SUCCESS;
    }

    public void init() {
        log.info("init");
    }

    public void destroy() {
        log.info("destory");
    }

    /**
     * 获取临时令牌
     *
     * 模拟登陆接口，获取模拟 Token
     * @return
     */
    public static String getTemporaryToken() {
        RedisUtil redisUtil = SpringContextUtils.getBean(RedisUtil.class);
        // 模拟登录生成Token
        String token = JwtUtil.sign("system", "System123456!");
        // 设置Token缓存有效时间为 5 分钟
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, 5 * 60 * 1000);
        return token;
    }

}


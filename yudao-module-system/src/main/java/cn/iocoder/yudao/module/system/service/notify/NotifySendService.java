package cn.iocoder.yudao.module.system.service.notify;

import java.util.Map;

/**
 * 站内信发送 Service 接口
 *
 * @author xrcoder
 */
public interface NotifySendService {
    /**
     * 发送单条站内信给用户
     *
     * @param userId         用户编号
     * @param templateCode   站内信模板编号
     * @param templateParams 站内信模板参数
     * @return 发送日志编号
     */
    Long sendSingleNotify(Long userId, String templateCode, Map<String, Object> templateParams);
}

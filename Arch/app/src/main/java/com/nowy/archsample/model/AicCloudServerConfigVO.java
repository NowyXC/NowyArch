package com.nowy.archsample.model;

import com.blankj.utilcode.util.StringUtils;

/**
 * @ClassName: CloudServerConfigBean
 * @Description: java类作用描述
 * @Author: Nowy
 * @CreateDate: 2020/6/4 9:46
 * @UpdateUser:
 * @UpdateDate: 2020/6/4 9:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AicCloudServerConfigVO {

    /**
     * 主机地址
     */
    private String hostaddress;
    /**
     * 备注
     */
    private String remark;
    /**
     * 绑定账号
     */
    private String bindcount;
    /**
     * 绑定学校码
     */
    private String bindschool;
    /**
     * NVR地址
     */
    private String nvraddress;
    /**
     * 云平台服务器地址
     */
    private String cloudServerUrl;
    /**
     * 旷视云服务器地址
     */
    private String openVisionUrl;
    /**
     * 绑定旷视云密码
     */
    private String openVisionPwd;


    private String dljUrl;

    private String dljAccount;

    private String dljPassword;

    public String getHostaddress() {
        return hostaddress;
    }

    public void setHostaddress(String hostaddress) {
        this.hostaddress = hostaddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBindcount() {
        if (!StringUtils.isEmpty(bindcount)){
            bindcount = bindcount.trim(); //去除账号空格
        }
        return bindcount;
    }

    public void setBindcount(String bindcount) {
        this.bindcount = bindcount;
    }

    public String getBindschool() {
        if (!StringUtils.isEmpty(bindschool)){
            bindschool = bindschool.trim(); //去除学校码空格
        }
        return bindschool;
    }

    public void setBindschool(String bindschool) {
        this.bindschool = bindschool;
    }

    public String getNvraddress() {
        return nvraddress;
    }

    public void setNvraddress(String nvraddress) {
        this.nvraddress = nvraddress;
    }

    public String getCloudServerUrl() {
        if (!StringUtils.isEmpty(cloudServerUrl)){
            cloudServerUrl = cloudServerUrl.trim(); //去除服务器地址空格
        }
        return cloudServerUrl;
    }

    public void setCloudServerUrl(String cloudServerUrl) {
        this.cloudServerUrl = cloudServerUrl;
    }

    public String getOpenVisionUrl() {
        if (!StringUtils.isEmpty(openVisionUrl)){
            openVisionUrl = openVisionUrl.trim(); //去除服务器地址空格
        }
        return openVisionUrl;
    }

    public void setOpenVisionUrl(String openVisionUrl) {
        this.openVisionUrl = openVisionUrl;
    }

    public String getOpenVisionPwd() {
        if (!StringUtils.isEmpty(openVisionPwd)){
            openVisionPwd = openVisionPwd.trim(); //去除密码空格
        }
        return openVisionPwd;
    }

    public void setOpenVisionPwd(String openVisionPwd) {
        this.openVisionPwd = openVisionPwd;
    }



    public String getDljUrl() {
        return dljUrl;
    }

    public void setDljUrl(String dljUrl) {
        this.dljUrl = dljUrl;
    }

    public String getDljAccount() {
        return dljAccount;
    }

    public void setDljAccount(String dljAccount) {
        this.dljAccount = dljAccount;
    }

    public String getDljPassword() {
        return dljPassword;
    }

    public void setDljPassword(String dljPassword) {
        this.dljPassword = dljPassword;
    }
}

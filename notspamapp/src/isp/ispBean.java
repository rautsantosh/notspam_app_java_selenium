/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isp;

/**
 *
 * @author rakeshv
 */
public class ispBean {

    private String email;
    private String password;
    private String proxyIP;
    private String proxyPort;
    private String chromeDriverPath = "C:\\notspam\\chromedriver.exe";
    private String proxyUsername;
    private String proxyPassword;
    private String recoveryEmailId;
    private String emailTo;
    private String actRecoveryEmail;
    private String newPassowrd;
    private int taskId;
    private int statusCode;
    private String statusText;
    private String subject;
    private String fromName;
    private String actionType;
    private String actProcessInbox;
    private String actProcessJunk;
    private String actComposeMail;
    private String actArchiveDelete;
    private String actBulkNotJunk;
    private String actDeleteAll;
    private String actSafeSender;
    private String actColorCategory;
    private String actAddContact;
    private String actMarkFlag;
    private String actClickLink;
    private String actReplyTo;
    private String actForwardMail;
    private String actChangePassword;
    private String forward_mail;
    private String report_not_spam;
    private String forward_to;
    private int loopCount;
    private int inboxCount;
    private int notJunkCount;
    private int deleteCount;
    private int replyCount;
    private int flagCount;
    private int forwardCount;
    private int cc_count;
    private int ss_count;
    private int ac_count;
    private int userId;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the proxyIP
     */
    public String getProxyIP() {
        return proxyIP;
    }

    /**
     * @param proxyIP the proxyIP to set
     */
    public void setProxyIP(String proxyIP) {
        this.proxyIP = proxyIP;
    }

    /**
     * @return the proxyPort
     */
    public String getProxyPort() {
        return proxyPort;
    }

    /**
     * @param proxyPort the proxyPort to set
     */
    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * @return the chromeDriverPath
     */
    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    /**
     * @param chromeDriverPath the chromeDriverPath to set
     */
    public void setChromeDriverPath(String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    /**
     * @return the proxyUsername
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    /**
     * @param proxyUsername the proxyUsername to set
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    /**
     * @return the proxyPassword
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * @param proxyPassword the proxyPassword to set
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    /**
     * @return the recoveryEmailId
     */
    public String getRecoveryEmailId() {
        return recoveryEmailId;
    }

    /**
     * @param recoveryEmailId the recoveryEmailId to set
     */
    public void setRecoveryEmailId(String recoveryEmailId) {
        this.recoveryEmailId = recoveryEmailId;
    }

    /**
     * @return the emailTo
     */
    public String getEmailTo() {
        return emailTo;
    }

    /**
     * @param emailTo the emailTo to set
     */
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    /**
     * @return the actRecoveryEmail
     */
    public String getActRecoveryEmail() {
        return actRecoveryEmail;
    }

    /**
     * @param actRecoveryEmail the actRecoveryEmail to set
     */
    public void setActRecoveryEmail(String actRecoveryEmail) {
        this.actRecoveryEmail = actRecoveryEmail;
    }

    /**
     * @return the newPassowrd
     */
    public String getNewPassowrd() {
        return newPassowrd;
    }

    /**
     * @param newPassowrd the newPassowrd to set
     */
    public void setNewPassowrd(String newPassowrd) {
        this.newPassowrd = newPassowrd;
    }

    /**
     * @return the taskId
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the statusText
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * @param statusText the statusText to set
     */
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the fromName
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * @param fromName the fromName to set
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * @return the actProcessInbox
     */
    public String getActProcessInbox() {
        return actProcessInbox;
    }

    /**
     * @param actProcessInbox the actProcessInbox to set
     */
    public void setActProcessInbox(String actProcessInbox) {
        this.actProcessInbox = actProcessInbox;
    }

    /**
     * @return the actProcessJunk
     */
    public String getActProcessJunk() {
        return actProcessJunk;
    }

    /**
     * @param actProcessJunk the actProcessJunk to set
     */
    public void setActProcessJunk(String actProcessJunk) {
        this.actProcessJunk = actProcessJunk;
    }

    /**
     * @return the actComposeMail
     */
    public String getActComposeMail() {
        return actComposeMail;
    }

    /**
     * @param actComposeMail the actComposeMail to set
     */
    public void setActComposeMail(String actComposeMail) {
        this.actComposeMail = actComposeMail;
    }

    /**
     * @return the actArchiveDelete
     */
    public String getActArchiveDelete() {
        return actArchiveDelete;
    }

    /**
     * @param actArchiveDelete the actArchiveDelete to set
     */
    public void setActArchiveDelete(String actArchiveDelete) {
        this.actArchiveDelete = actArchiveDelete;
    }

    /**
     * @return the actBulkNotJunk
     */
    public String getActBulkNotJunk() {
        return actBulkNotJunk;
    }

    /**
     * @param actBulkNotJunk the actBulkNotJunk to set
     */
    public void setActBulkNotJunk(String actBulkNotJunk) {
        this.actBulkNotJunk = actBulkNotJunk;
    }

    /**
     * @return the actDeleteAll
     */
    public String getActDeleteAll() {
        return actDeleteAll;
    }

    /**
     * @param actDeleteAll the actDeleteAll to set
     */
    public void setActDeleteAll(String actDeleteAll) {
        this.actDeleteAll = actDeleteAll;
    }

    /**
     * @return the actSafeSender
     */
    public String getActSafeSender() {
        return actSafeSender;
    }

    /**
     * @param actSafeSender the actSafeSender to set
     */
    public void setActSafeSender(String actSafeSender) {
        this.actSafeSender = actSafeSender;
    }

    /**
     * @return the actColorCategory
     */
    public String getActColorCategory() {
        return actColorCategory;
    }

    /**
     * @param actColorCategory the actColorCategory to set
     */
    public void setActColorCategory(String actColorCategory) {
        this.actColorCategory = actColorCategory;
    }

    /**
     * @return the actAddContact
     */
    public String getActAddContact() {
        return actAddContact;
    }

    /**
     * @param actAddContact the actAddContact to set
     */
    public void setActAddContact(String actAddContact) {
        this.actAddContact = actAddContact;
    }

    /**
     * @return the actClickLink
     */
    public String getActClickLink() {
        return actClickLink;
    }

    /**
     * @param actClickLink the actClickLink to set
     */
    public void setActClickLink(String actClickLink) {
        this.actClickLink = actClickLink;
    }

    /**
     * @return the actReplyTo
     */
    public String getActReplyTo() {
        return actReplyTo;
    }

    /**
     * @param actReplyTo the actReplyTo to set
     */
    public void setActReplyTo(String actReplyTo) {
        this.actReplyTo = actReplyTo;
    }

    /**
     * @return the actForwardMail
     */
    public String getActForwardMail() {
        return actForwardMail;
    }

    /**
     * @param actForwardMail the actForwardMail to set
     */
    public void setActForwardMail(String actForwardMail) {
        this.actForwardMail = actForwardMail;
    }

    /**
     * @return the actChangePassword
     */
    public String getActChangePassword() {
        return actChangePassword;
    }

    /**
     * @param actChangePassword the actChangePassword to set
     */
    public void setActChangePassword(String actChangePassword) {
        this.actChangePassword = actChangePassword;
    }

    /**
     * @return the loopCount
     */
    public int getLoopCount() {
        return loopCount;
    }

    /**
     * @param loopCount the loopCount to set
     */
    public void setLoopCount(int loopCount) {
        this.loopCount = loopCount;
    }

    /**
     * @return the inboxCount
     */
    public int getInboxCount() {
        return inboxCount;
    }

    /**
     * @param inboxCount the inboxCount to set
     */
    public void setInboxCount(int inboxCount) {
        this.inboxCount = inboxCount;
    }

    /**
     * @return the notJunkCount
     */
    public int getNotJunkCount() {
        return notJunkCount;
    }

    /**
     * @param notJunkCount the notJunkCount to set
     */
    public void setNotJunkCount(int notJunkCount) {
        this.notJunkCount = notJunkCount;
    }

    /**
     * @return the deleteCount
     */
    public int getDeleteCount() {
        return deleteCount;
    }

    /**
     * @param deleteCount the deleteCount to set
     */
    public void setDeleteCount(int deleteCount) {
        this.deleteCount = deleteCount;
    }

    /**
     * @return the replyCount
     */
    public int getReplyCount() {
        return replyCount;
    }

    /**
     * @param replyCount the replyCount to set
     */
    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * @return the flagCount
     */
    public int getFlagCount() {
        return flagCount;
    }

    /**
     * @param flagCount the flagCount to set
     */
    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the actMarkFlag
     */
    public String getActMarkFlag() {
        return actMarkFlag;
    }

    /**
     * @param actMarkFlag the actMarkFlag to set
     */
    public void setActMarkFlag(String actMarkFlag) {
        this.actMarkFlag = actMarkFlag;
    }

    /**
     * @return the forward_mail
     */
    public String getForward_mail() {
        return forward_mail;
    }

    /**
     * @param forward_mail the forward_mail to set
     */
    public void setForward_mail(String forward_mail) {
        this.forward_mail = forward_mail;
    }

    /**
     * @return the report_not_spam
     */
    public String getReport_not_spam() {
        return report_not_spam;
    }

    /**
     * @param report_not_spam the report_not_spam to set
     */
    public void setReport_not_spam(String report_not_spam) {
        this.report_not_spam = report_not_spam;
    }

    /**
     * @return the forward_to
     */
    public String getForward_to() {
        return forward_to;
    }

    /**
     * @param forward_to the forward_to to set
     */
    public void setForward_to(String forward_to) {
        this.forward_to = forward_to;
    }

    /**
     * @return the forwardCount
     */
    public int getForwardCount() {
        return forwardCount;
    }

    /**
     * @param forwardCount the forwardCount to set
     */
    public void setForwardCount(int forwardCount) {
        this.forwardCount = forwardCount;
    }

    /**
     * @return the cc_count
     */
    public int getCc_count() {
        return cc_count;
    }

    /**
     * @param cc_count the cc_count to set
     */
    public void setCc_count(int cc_count) {
        this.cc_count = cc_count;
    }

    /**
     * @return the ss_count
     */
    public int getSs_count() {
        return ss_count;
    }

    /**
     * @param ss_count the ss_count to set
     */
    public void setSs_count(int ss_count) {
        this.ss_count = ss_count;
    }

    /**
     * @return the ac_count
     */
    public int getAc_count() {
        return ac_count;
    }

    /**
     * @param ac_count the ac_count to set
     */
    public void setAc_count(int ac_count) {
        this.ac_count = ac_count;
    }


}

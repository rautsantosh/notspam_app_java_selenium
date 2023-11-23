/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notspamapp;

import isp.*;
import java.sql.*;
import utility.CustomException;
import utility.DBConnect;

/**
 *
 * @author rakeshv
 */
public class WarmUp {

    private static int totalCount = 0;

    public WarmUp() {
        totalCount++;
    }

    public static int getActiveCount() {
        return totalCount;
    }

    public static void decrementActiveCount() {
        totalCount--;
    }

    private String getISPDomain(String email) {
        String isp = null;
        email = email.toString().trim().toLowerCase();
        String[] emailParts = email.split("@");
        if (emailParts.length > 1) {
            isp = emailParts[1];
        }
        return isp;
    }

    private String getISPByDomain(String emailDomain) {
        String ispName = "";
        if (emailDomain.equals("gmail.com")) {
            ispName = "gmail";
        } else if (emailDomain.equals("yahoo.com")) {
            ispName = "yahoo";
        } else if (emailDomain.equals("att.net")) {
            ispName = "att";
        } else if (emailDomain.equals("hotmail.com") || emailDomain.equals("outlook.com")) {
            ispName = "hotmail";
        } else if (emailDomain.equals("aol.com") || emailDomain.equals("aim.com")) {
            ispName = "aol";
        }
        return ispName;
    }

    public void checkMultiple() throws CustomException {
        try {
            Thread.sleep(10000);
            System.out.println("Running..");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new CustomException("", "");
        }
    }

    public void startWarming() throws Exception {
        Connection connection = null;
        try {
            connection = DBConnect.mysqlConnection();
            String windowsUser = System.getProperty("user.name").toLowerCase();
            if (windowsUser.equalsIgnoreCase("administrator1")) {
                windowsUser = "teamJ";
            }
            System.out.println("USER --> " + windowsUser);
            String sqlString = null;
            if (windowsUser.toLowerCase().contains("euser0")) {
                sqlString = "SELECT A.id, A.email, A.password, A.proxy_ip, A.proxy_port, A.proxy_username, A.proxy_password, A.forward_to, A.email_to, A.recovery_email as recovery_email_id, A.new_password, A.task_id, A.status_code, A.status_text, B.subject, B.from_name, B.action_type, B.process_inbox, B.process_junk, B.compose_mail, B.archive_delete, B.bulk_not_junk, B.forward_count, B.cc_count, B.ss_count, B.ac_count, B.delete_all, B.safe_sender, B.color_category, B.add_contact, B.mark_flag, B.click_link, B.reply_to, B.forward_mail, B.recovery_email, B.change_passwd, B.loop_count, B.inbox_count, B.not_junk_count, B.delete_count, B.reply_count, B.flag_count, B.user_id FROM not_spam_list AS A INNER JOIN task_master AS B ON A.task_id = B.id INNER JOIN user_master AS C ON C.id = B.user_id WHERE A.status_code =0 and C.name='" + windowsUser + "' LIMIT 1";
            } else { 
                sqlString = "SELECT A.id, A.email, A.password, A.proxy_ip, A.proxy_port, A.proxy_username, A.proxy_password, A.email_to, A.recovery_email as recovery_email_id, A.forward_to, A.new_password, A.task_id, A.status_code, A.status_text, B.subject, B.from_name, B.action_type, B.process_inbox, B.process_junk, B.compose_mail, B.forward_mail, B.report_not_spam, B.forward_count, B.cc_count, B.ss_count, B.ac_count, B.archive_delete, B.bulk_not_junk, B.delete_all, B.safe_sender, B.color_category, B.add_contact, B.mark_flag, B.click_link, B.reply_to, B.forward_mail, B.recovery_email, B.change_passwd, B.loop_count, B.inbox_count, B.not_junk_count, B.delete_count, B.reply_count, B.flag_count, B.user_id FROM not_spam_list AS A INNER JOIN task_master AS B ON A.task_id = B.id INNER JOIN user_master AS C ON C.id = B.user_id WHERE A.status_code = 0 LIMIT 1";
            }

            PreparedStatement ps = connection.prepareStatement(sqlString);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ispBean bean = new ispBean();
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setProxyIP(rs.getString("proxy_ip"));
                bean.setProxyPort(rs.getString("proxy_port"));
                bean.setProxyUsername(rs.getString("proxy_username"));
                bean.setProxyPassword(rs.getString("proxy_password"));
                bean.setEmailTo(rs.getString("email_to"));
                bean.setForward_to(rs.getString("forward_to"));
                bean.setForward_mail(rs.getString("forward_mail"));
                bean.setReport_not_spam(rs.getString("report_not_spam"));
                bean.setRecoveryEmailId(rs.getString("recovery_email_id"));
                bean.setNewPassowrd(rs.getString("new_password"));
                bean.setTaskId(rs.getInt("task_id"));
                bean.setStatusCode(rs.getInt("status_code"));
                bean.setStatusText(rs.getString("status_text"));
                bean.setSubject(rs.getString("subject"));
                bean.setFromName(rs.getString("from_name"));
                bean.setActionType(rs.getString("action_type"));
                bean.setActProcessInbox(rs.getString("process_inbox"));
                bean.setActProcessJunk(rs.getString("process_junk"));
                bean.setActComposeMail(rs.getString("compose_mail"));
                bean.setActArchiveDelete(rs.getString("archive_delete"));
                bean.setActBulkNotJunk(rs.getString("bulk_not_junk"));
                bean.setActDeleteAll(rs.getString("delete_all"));
                bean.setActSafeSender(rs.getString("safe_sender"));
                bean.setActColorCategory(rs.getString("color_category"));
                bean.setActAddContact(rs.getString("add_contact"));
                bean.setActMarkFlag(rs.getString("mark_flag"));
                bean.setActClickLink(rs.getString("click_link"));
                bean.setActReplyTo(rs.getString("reply_to"));
                bean.setActForwardMail(rs.getString("forward_mail"));
                bean.setActRecoveryEmail(rs.getString("recovery_email"));
                bean.setActChangePassword(rs.getString("change_passwd"));
                bean.setLoopCount(rs.getInt("loop_count"));
                bean.setInboxCount(rs.getInt("inbox_count"));
                bean.setNotJunkCount(rs.getInt("not_junk_count"));
                bean.setDeleteCount(rs.getInt("delete_count"));
                bean.setReplyCount(rs.getInt("reply_count"));
                bean.setFlagCount(rs.getInt("flag_count"));
                bean.setForwardCount(rs.getInt("forward_count"));
                bean.setSs_count(rs.getInt("ss_count"));
                bean.setCc_count(rs.getInt("cc_count"));
                bean.setAc_count(rs.getInt("ac_count"));
                bean.setUserId(rs.getInt("user_id"));

                String emailId = rs.getString("email").trim();
                int seedId = rs.getInt("id");
                String updateQuery = "UPDATE not_spam_list SET status_code = ? , status_text = ? WHERE id = ?";
                PreparedStatement ps1 = connection.prepareStatement(updateQuery);
                ps1.setInt(1, 1);
                ps1.setString(2, "in progress");
                ps1.setInt(3, seedId);
                int flag = ps1.executeUpdate();
                if (flag == 1) {
                    System.out.println("Processing : " + emailId);
                    String isp = this.getISPByDomain(this.getISPDomain(emailId)).toLowerCase();
                    String successMessage = "Success";
                    int messageCode = 200;
                    try {
                        if (isp.equals("hotmail")) {
                            System.out.println("Hotmail called");
                            Hotmail hotmail = new Hotmail();
                            hotmail.setBean(bean);
                            boolean isFound = hotmail.startProcess();
                            if (isFound == false) {
                                successMessage = "Success";
                                messageCode = 200;
                            }
                        }
                        updateQuery = "UPDATE not_spam_list SET status_code = ? , status_text = ? WHERE id = ?";
                        System.out.println(updateQuery);
                        PreparedStatement ps2 = connection.prepareStatement(updateQuery);
                        ps2.setInt(1, messageCode);
                        ps2.setString(2, successMessage);
                        ps2.setInt(3, seedId);
                        ps2.executeUpdate();
                        System.out.println("SUCCESS: " + successMessage);
                    } catch (CustomException e) {
                        e.printStackTrace();
                        System.out.println("============================== EXCEPTION ============================");
                        try {
                            updateQuery = "UPDATE not_spam_list SET status_code = ? , status_text = ? WHERE id = ?";
                            PreparedStatement ps2 = connection.prepareStatement(updateQuery);
                            ps2.setInt(1, Integer.parseInt(e.getErrorCode().trim()));
                            ps2.setString(2, e.getMessage().trim());
                            ps2.setInt(3, seedId);
                            ps2.executeUpdate();
                            System.out.println(ps2);
                        } catch (Exception ex) {
                            System.out.println("Execption " + ex.getMessage());
                        }
                    }
                } else {
                    System.out.println("not calling..");
                }
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Execption II" + e.getMessage());
            connection.close();
            e.printStackTrace();
        }
    }

}

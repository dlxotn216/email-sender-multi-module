<#-- @ftlvariable name="mvr" type="java.util.Map<String, Sting>" -->
<#-- @ftlvariable name="systemURL" type="java.lang.String" -->
<#-- @ftlvariable name="sender" type="java.lang.String" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- fonts -->
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700" rel="stylesheet" />
    <style type="text/css">
        table, th, td {font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;}
    </style>
</head>

<body bgcolor="#eee" style="margin:0; padding:0;">

<table width="640" border="0" cellpadding="0" cellspacing="0" bgcolor="#ffffff" style="margin:20px auto">
    <tr>
        <td colspan="3" bgcolor="#37a6ff" height="8" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
        <td style="padding:40px 0; text-align:center">
            <img src="cid:logo.png" alt="CTMS Mail Log" height="24" width="192"/>
        </td>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
        <td valign="top" style="padding-bottom:20px; color:#363636; font-size: 20px; line-height:1.3; text-align:left; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">
            <strong>Notification of MVR Rejection regarding Project ${mvr.protocol}</strong>
        </td>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
        <td height="70" valign="top" style="padding-bottom:26px; color:#212121; line-height:1.86; font-size:14px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">
            Your submitted Monitoring Visit Report has been rejected. Details below.

        </td>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
        <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top :15px; margin-bottom : 25px;">
                <tr>
                    <td bgcolor="#f5f5f5" style="width:25%; padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">Project</td>
                    <td bgcolor="#f5f5f5" style="width:75%; padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">${mvr.protocol}</td>
                </tr>
                <tr>
                    <td bgcolor="#ffffff" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">MVR Type</td>
                    <td bgcolor="#ffffff" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">${mvr.typeName}</td>
                </tr>
                <tr>
                    <td bgcolor="#f5f5f5" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">Site</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">${mvr.siteName}</td>
                </tr>
                <tr>
                    <td bgcolor="#ffffff" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">Visit</td>
                    <td bgcolor="#ffffff" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">${mvr.visitNo}</td>
                </tr>
                <tr>
                    <td bgcolor="#f5f5f5" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">Approver</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">${mvr.approver}</td>
                </tr>
                <tr>
                    <td bgcolor="#ffffff" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">Reason</td>
                    <td bgcolor="#ffffff" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">${(mvr.reason)!"&nbsp;"}</td>
                </tr>
                <tr>
                    <td bgcolor="#f5f5f5" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">URL</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 0; color:#363636; font-size:14px; padding-left:24px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;"><a href="${systemURL}${mvr.getRelatedURL()}" style="color:#54aaef; text-decoration:underline; font-size:14px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">Go to page</a></td>
                </tr>
            </table>
        </td>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
        <td style="text-align:left;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="24" style="font-size:0; line-height:0;">&nbsp;</td>
                    <td colspan="2" style="padding-top:50px; padding-bottom:16px; font-size:14px; font-weight:bold; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif; color:#363636">Question</td>
                </tr>
                <tr>
                    <td width="24" style="font-size:0; line-height:0;">&nbsp;</td>
                    <td colspan="2" style="padding-bottom:40px; font-size:12px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif; line-height:1.33; color:#6d7278">This is an outgoing email only please do not reply. Please send your message to the below email address. <strong>${sender?default('help@crscube.co.kr')}</strong></td>
                </tr>
                <tr>
                    <td width="24" style="font-size:0; line-height:0;">&nbsp;</td>
                    <td valign="middle" style="font-size:8px; color:#363636; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">COPYRIGHT 2018 by CRSCUBE ALL RIGHTS RESERVED.</td>
                    <td valign="middle" width="100" id="title_log_text" style="font-size:16px; font-weight:500; vertical-align:middle; text-align:right; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif; color:#6d7278" >CRScube</td>
                </tr>
            </table>
        </td>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="3" height="20" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
</table>
</body>
</html>
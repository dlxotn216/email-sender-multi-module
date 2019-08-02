<#-- @ftlvariable name="alarms" type="java.util.List<kr.co.crscube.ctms.model.batchjob.MvrDueAlarm>" -->
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
<table width="960" border="0" cellpadding="0" cellspacing="0" bgcolor="#ffffff" style="margin:20px auto">
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
            <strong>Notification: Due date of MVR task is approaching</strong>
        </td>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
        <td height="70" valign="top" style="padding-bottom:26px; color:#212121; line-height:1.86; font-size:14px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">
            Please note that due date of your MVR task is coming up.<br />
            By referring to the table below, please complete your assigned MVR task prior to due date.

        </td>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
        <td width="848">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list-email" style="table-layout:fixed; margin-top: 15px; margin-bottom: 25px;">
                <tr>
                    <td bgcolor="#f5f5f5" style="padding:6px 2px 6px 16px; width:5%; text-align:center; color:#363636; font-size:14px">No.</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 2px; width:20%; text-align:center; color:#363636; font-size:14px">Protocol</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 2px; width:10%; text-align:center; color:#363636; font-size:14px">MVR type</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 2px; width:30%; text-align:center; color:#363636; font-size:14px">Site</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 2px; width:8%; text-align:center; color:#363636; font-size:14px">Visit no.</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 2px; width:12%; text-align:center; color:#363636; font-size:14px">Visit date</td>
                    <td bgcolor="#f5f5f5" style="padding:6px 2px; width:15%; text-align:center; color:#363636; font-size:14px">Approval level</td>
                </tr>
                <#list alarms as alarm>
                <tr>
                    <td style="padding:6px 2px; border-bottom:1px solid #e6e6e8; text-align:center; color:#363636; font-size:12px; word-break: break-all">${alarm_index + 1}</td>
                    <td style="padding:6px 2px; border-bottom:1px solid #e6e6e8; text-align:center; color:#363636; font-size:12px; word-break: break-all">${alarm.protocol!""}</td>
                    <td style="padding:6px 2px; border-bottom:1px solid #e6e6e8; text-align:center; color:#363636; font-size:12px; word-break: break-all">${alarm.mvrType!""}</td>
                    <td style="padding:6px 2px; border-bottom:1px solid #e6e6e8; text-align:center; color:#363636; font-size:12px; word-break: break-all">[${alarm.teamId?xhtml}] ${alarm.teamName!""}</td>
                    <td style="padding:6px 2px; border-bottom:1px solid #e6e6e8; text-align:center; color:#363636; font-size:12px; word-break: break-all">${alarm.visitNo?c}</td>
                    <td style="padding:6px 2px; border-bottom:1px solid #e6e6e8; text-align:center; color:#363636; font-size:12px; word-break: break-all">${alarm.visitDate!""}</td>
                    <td style="padding:6px 2px; border-bottom:1px solid #e6e6e8; text-align:center; color:#363636; font-size:12px; word-break: break-all"><a href="${domainUrl!''}/${alarm.mvrLink!""}" style="color:#54aaef; text-decoration:underline; font-size:14px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif;">${alarm.approvalLabel!""}</a>
                </tr>
                </#list>
            </table>
        </td>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
    </tr>
    <tr>
        <td width="56" style="font-size:0; line-height:0;">&nbsp;</td>
        <td style="text-align:left;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2" style="padding-top:120px; padding-bottom:16px; font-size:14px; font-weight:bold; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif; color:#363636">Question</td>
                </tr>
                <tr>
                    <td colspan="2" style="padding-bottom:40px; font-size:12px; font-family: 'Noto Sans KR', Arial, Helvetica, sans-serif; line-height:1.33; color:#6d7278">This is an outgoing email only please do not reply. Please send your message to the email address. <strong>${sender?default('help@crscube.co.kr')}</strong></td>
                </tr>
                <tr>
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
<#-- @ftlvariable name="username" type="java.lang.String" -->
<#-- @ftlvariable name="userId" type="java.lang.String" -->
<#-- @ftlvariable name="password" type="java.lang.String" -->
<#-- @ftlvariable name="systemURL" type="java.lang.String" -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
</head>
<body>
<table width="600px" border = "0">
    <tr>
        <td style="padding	: 5px 20px 5px 20px;">
            <img src="cid:logo.png" alt="CTMS Mail Log" height="33" width="202"/>
        </td>
        <td id="title_log_text" style="font-size: 20px;letter-spacing: -0.8px;vertical-align: bottom;text-align: right;font-family: 'Noto Serif Medium';color: rgb(72,72,72);" >CRScube</td>
    </tr>
    <tr>
        <td colspan="2">
            <hr style="height: 2px;background-color: rgb(56, 165, 255);border: none;width: 600px;margin-top: -5px;">
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div style="font-size: 20px;letter-spacing: -1.2px;text-align:  left;font-family: 'Noto Serif Medium';">
                <strong>${sponsorName}(cubeCTMS) User Registration</strong>
            </div>
            <div style="padding-top:30px; font-size:12px;">
                Dear <strong>${username}</strong>,<br />
                You have been registered as a user for ${sponsorName}’s Clinical Trial Management System(cubeCTMS).<br />
                Please proceed to login to the system using the below information.
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <table width="100%" style="margin-top : 15px;margin-bottom : 25px;">
                <colgroup>
                    <col width = "20%"/>
                    <col width = "80%"/>
                </colgroup>
                <tr>
                    <td colspan="2" style="border-bottom : 1px dotted #CDC1A7;letter-spacing: -0.8px;font-size: 15px;padding-left: 8px;font-family: 'Noto Serif DemiLight';">User Information</td>
                </tr>
                <tr>
                    <td style="border-bottom : 1px dotted #CDC1A7;letter-spacing: -0.8px;font-size: 15px;padding-left: 8px;font-family: 'Noto Serif DemiLight';">Access URL</td>
                    <td style="border-bottom : 1px dotted #CDC1A7;letter-spacing: -0.8px;font-size: 15px;padding-left: 8px;font-family: 'Noto Serif Medium';"><a href="${systemURL}">${systemURL}</a></td>
                </tr>
                <tr>
                    <td style="border-bottom : 1px dotted #CDC1A7;letter-spacing: -0.8px;font-size: 15px;padding-left: 8px;font-family: 'Noto Serif DemiLight';">User ID</td>
                    <td style="border-bottom : 1px dotted #CDC1A7;letter-spacing: -0.8px;font-size: 15px;padding-left: 8px;font-family: 'Noto Serif Medium';">Same as for cubeCDMS</td>
                </tr>
                <tr>
                    <td style="letter-spacing: -0.8px;font-size: 15px;padding-left: 8px;font-family: 'Noto Serif DemiLight';">Password</td>
                    <td style="letter-spacing: -0.8px;font-size: 15px;padding-left: 8px;font-family: 'Noto Serif Medium';">Same as for cubeCDMS</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div>
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <colgroup>
                        <col width="160" />
                        <col width="" />
                    </colgroup>
                    <tr>
                        <td colspan="2" style="padding:5px 10px; width:120px; background-color:#f8f8f8; font-weight:bold; font-size:11px; text-align:center;">Personal Security Policy</td>
                    </tr>
                    <#--<tr>-->
                        <#--<td style="padding:5px 10px; width:120px; background-color:#f8f8f8; font-weight:bold; font-size:11px; text-align:center;">Temporary Password</td>-->
                        <#--<td style="padding:5px 10px; background-color:#ffffff;font-size:11px;">Login using your issued temporary password if you are a first time user. Temporary passwords must be changed upon first time usage.</td>-->
                    <#--</tr>-->
                    <tr>
                        <td style="padding:5px 10px; width:120px; background-color:#f8f8f8; font-weight:bold; font-size:11px; text-align:center;">Personal Password</td>
                        <td style="padding:5px 10px; background-color:#ffffff;font-size:11px;">Passwords are case-sensitive and must include both letters and numbers and be of a length of at least 8 characters.</td>
                    </tr>
                    <tr>
                        <td style="padding:5px 10px; width:120px; background-color:#f8f8f8; font-weight:bold; font-size:11px; text-align:center;">Password Expiry</td>
                        <td style="padding:5px 10px; background-color:#ffffff;font-size:11px;">Passwords expire and must be changed once every 90 days.</td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align:center; font-size:8px; padding-top:20px;">COPYRIGHT 2018 by CRSCUBE ALL RIGHTS RESERVED.</td>
    </tr>
</table>
</body>
</html>
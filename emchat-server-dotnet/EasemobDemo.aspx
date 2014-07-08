<%@ Page Language="C#" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/DTD/xhtml1-transitional.dtd">

<script runat="server">
    protected void Page_Load(object sender, EventArgs e) { }

    /// <summary>
    /// 设置app的基本信息
    /// </summary>
    string appName = "iotbao", orgName = "ulinkmedia";
    protected void btnQueryToken_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
                , clientSecret = txtClient_secret.Text.Trim();
            HXComm.EaseMobDemo easeMob = new HXComm.EaseMobDemo(clientID, clientSecret, appName, orgName);

            txtResponse.Text = easeMob.token;
        }
    }

    protected void btnCreateAccount_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
                , clientSecret = txtClient_secret.Text.Trim();
            HXComm.EaseMobDemo easeMob = new HXComm.EaseMobDemo(clientID, clientSecret,appName,orgName);
            txtResponse.Text = easeMob.AccountCreate(txtUserName.Text.Trim(), txtPwd.Text.Trim());
        }
    }

    protected void btnAccountGet_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
               , clientSecret = txtClient_secret.Text.Trim();
            HXComm.EaseMobDemo easeMob = new HXComm.EaseMobDemo(clientID, clientSecret,appName,orgName);
            txtResponse.Text = easeMob.AccountGet(txtUserName.Text.Trim());
        }
    }

    protected void btnAccountDel_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
               , clientSecret = txtClient_secret.Text.Trim();
           HXComm.EaseMobDemo easeMob = new HXComm.EaseMobDemo(clientID, clientSecret,appName,orgName);
            txtResponse.Text = easeMob.AccountDel(txtUserName.Text.Trim());
        }
    }

    protected void btnAccountResetPwd_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
               , clientSecret = txtClient_secret.Text.Trim();
            HXComm.EaseMobDemo easeMob = new HXComm.EaseMobDemo(clientID, clientSecret,appName,orgName);
            txtResponse.Text = easeMob.AccountResetPwd(txtUserName.Text.Trim(), txtPwd.Text);
        }
    }
</script>

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>环信C# Demo</title>
    <style type="text/css">
    body{ margin:0; padding:0; font-size:12px; }
    .msgContainer{ padding:20px; }
    .baiduPush{ margin:10; padding:10px;list-style:none; list-style-type:none;}
    .baiduPush li{ width:98%; margin:0; padding:0px; line-height:230%;list-style:none; list-style-type:none;}
    .baiduPush li span{ width:160px; text-align:right; float:left; display:block;}
    .clear{ clear:both; }
    </style>
</head>
<body><form id="form1" runat="server">
<div class="msgContainer">
  <ul class="baiduPush">
    <li><span>client_id:</span><asp:TextBox ID="txtClient_id" runat="server" Width="400px"/><div class="clear"></div></li>
    <li><span>client_secret:</span><asp:TextBox ID="txtClient_secret" runat="server" Width="400px"/><div class="clear"></div></li>
    <li><span>&nbsp;</span><asp:Button ID="btnQueryToken" runat="server" Text="QueryToken" onclick="btnQueryToken_Click" /><div class="clear"></div></li>
    
    <li><span>username:</span><asp:TextBox ID="txtUserName" runat="server" Width="400px"/><div class="clear"></div></li>
    <li><span>password:</span><asp:TextBox ID="txtPwd" runat="server" Width="400px" /><div class="clear"></div></li>
    <li><span>&nbsp;</span><asp:Button ID="btnCreateAccount" runat="server" Text="CreateAccount" onclick="btnCreateAccount_Click" /><div class="clear"></div></li>
    <li><span>&nbsp;</span><asp:Button ID="btnAccountGet" runat="server" Text="Account Get" onclick="btnAccountGet_Click" /><div class="clear"></div></li>
    <li><span>&nbsp;</span><asp:Button ID="btnAccountDel" runat="server" Text="Account Del" onclick="btnAccountDel_Click" /><div class="clear"></div></li>
    <li><span>&nbsp;</span><asp:Button ID="btnAccountResetPwd" runat="server" Text="Account Reset Password" onclick="btnAccountResetPwd_Click" /><div class="clear"></div></li>

    <li><span>&nbsp;</span><div class="clear"></div></li>
    <li><span>Response:</span><asp:TextBox ID="txtResponse" runat="server" Width="600px" Rows="10" Columns="80" TextMode="MultiLine"/><div class="clear"></div></li>
  </ul>
</div>
</form></body>
</html>

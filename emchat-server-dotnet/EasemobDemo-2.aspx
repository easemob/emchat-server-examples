<%@ Page Language="C#" %>
<%@ Import Namespace="System.IO" %>
<%@ Import Namespace="System.Net" %>
<%@ Import Namespace="Newtonsoft.Json.Linq" %>

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
            EaseMobDemo easeMob = new EaseMobDemo(clientID, clientSecret, appName, orgName);

            txtResponse.Text = easeMob.token;
        }
    }

    protected void btnCreateAccount_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
                , clientSecret = txtClient_secret.Text.Trim();
            EaseMobDemo easeMob = new EaseMobDemo(clientID, clientSecret,appName,orgName);
            txtResponse.Text = easeMob.AccountCreate(txtUserName.Text.Trim(), txtPwd.Text.Trim());
        }
    }

    protected void btnAccountGet_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
               , clientSecret = txtClient_secret.Text.Trim();
            EaseMobDemo easeMob = new EaseMobDemo(clientID, clientSecret,appName,orgName);
            txtResponse.Text = easeMob.AccountGet(txtUserName.Text.Trim());
        }
    }

    protected void btnAccountDel_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
               , clientSecret = txtClient_secret.Text.Trim();
            EaseMobDemo easeMob = new EaseMobDemo(clientID, clientSecret,appName,orgName);
            txtResponse.Text = easeMob.AccountDel(txtUserName.Text.Trim());
        }
    }

    protected void btnAccountResetPwd_Click(object sender, EventArgs e)
    {
        if (IsValid)
        {
            string clientID = txtClient_id.Text.Trim()
               , clientSecret = txtClient_secret.Text.Trim();
            EaseMobDemo easeMob = new EaseMobDemo(clientID, clientSecret,appName,orgName);
            txtResponse.Text = easeMob.AccountResetPwd(txtUserName.Text.Trim(), txtPwd.Text);
        }
    }

    /// <summary>
    /// 环信服务器端会员访问接口Demo
    /// Author：Mr.Hu
    /// QQ:346163801
    /// Email:346163801@qq.com
    /// 如有任何问题，可QQ或邮箱联系
    /// </summary>
    public class EaseMobDemo
    {
        string reqUrlFormat = "https://a1.easemob.com/{0}/{1}/";
        public string clientID { get; set; }
        public string clientSecret { get; set; }
        public string appName { get; set; }
        public string orgName { get; set; }
        public string token { get; set; }
        public string easeMobUrl { get { return string.Format(reqUrlFormat, orgName, appName); } }

        public EaseMobDemo(string easeAppClientID, string easeAppClientSecret, string easeAppName, string easeAppOrgName)
        {
            this.clientID = easeAppClientID;
            this.clientSecret = easeAppClientSecret;
            this.appName = easeAppName;
            this.orgName = easeAppOrgName;
            this.token = QueryToken();
        }

        /// <summary>
        /// 使用app的client_id 和 client_secret登陆并获取授权token
        /// </summary>
        /// <returns></returns>
        string QueryToken()
        {
            if (string.IsNullOrEmpty(clientID) || string.IsNullOrEmpty(clientSecret)) { return string.Empty; }
            string cacheKey = clientID + clientSecret;
            if (System.Web.HttpRuntime.Cache.Get(cacheKey) != null &&
                System.Web.HttpRuntime.Cache.Get(cacheKey).ToString().Length > 0)
            {
                return System.Web.HttpRuntime.Cache.Get(cacheKey).ToString();
            }

            string postUrl = easeMobUrl + "token";
            StringBuilder _build = new StringBuilder();
            _build.Append("{");
            _build.AppendFormat("\"grant_type\": \"client_credentials\",\"client_id\": \"{0}\",\"client_secret\": \"{1}\"", clientID, clientSecret);
            _build.Append("}");

            string postResultStr = ReqUrl(postUrl, "POST", _build.ToString(), string.Empty);
            string token = string.Empty;
            int expireSeconds = 0;
            try
            {
                JObject jo = JObject.Parse(postResultStr);
                token = jo.GetValue("access_token").ToString();
                int.TryParse(jo.GetValue("expires_in").ToString(), out expireSeconds);
                //设置缓存
                if (!string.IsNullOrEmpty(token) && token.Length > 0 && expireSeconds > 0)
                {
                    System.Web.HttpRuntime.Cache.Insert(cacheKey, token, null, DateTime.Now.AddSeconds(expireSeconds), System.TimeSpan.Zero);
                }
            }
            catch { return postResultStr; }
            return token;
        }

        /// <summary>
        /// 创建用户
        /// </summary>
        /// <param name="userName">账号</param>
        /// <param name="password">密码</param>
        /// <returns>创建成功的用户JSON</returns>
        public string AccountCreate(string userName, string password)
        {
            StringBuilder _build = new StringBuilder();
            _build.Append("{");
            _build.AppendFormat("\"username\": \"{0}\",\"password\": \"{1}\"", userName, password);
            _build.Append("}");

            return AccountCreate(_build.ToString());
        }

        /// <summary>
        /// 创建用户(可以批量创建)
        /// </summary>
        /// <param name="postData">创建账号JSON数组--可以一个，也可以多个</param>
        /// <returns>创建成功的用户JSON</returns>
        public string AccountCreate(string postData) { return ReqUrl(easeMobUrl + "users", "POST", postData, token); }

        /// <summary>
        /// 获取指定用户详情
        /// </summary>
        /// <param name="userName">账号</param>
        /// <returns>会员JSON</returns>
        public string AccountGet(string userName) { return ReqUrl(easeMobUrl + "users/" + userName, "GET", string.Empty, token); }

        /// <summary>
        /// 重置用户密码
        /// </summary>
        /// <param name="userName">账号</param>
        /// <param name="newPassword">新密码</param>
        /// <returns>重置结果JSON(如：{ "action" : "set user password",  "timestamp" : 1404802674401,  "duration" : 90})</returns>
        public string AccountResetPwd(string userName, string newPassword) { return ReqUrl(easeMobUrl + "users/" + userName + "/password", "PUT", "{\"newpassword\" : \"" + newPassword + "\"}", token); }

        /// <summary>
        /// 删除用户
        /// </summary>
        /// <param name="userName">账号</param>
        /// <returns>成功返回会员JSON详细信息，失败直接返回：系统错误信息</returns>
        public string AccountDel(string userName)
        {
            string postUrl = easeMobUrl + "users/" + userName;

            return ReqUrl(postUrl, "DELETE", string.Empty, token);
        }

        public string ReqUrl(string reqUrl, string method, string paramData, string token)
        {
            try
            {
                HttpWebRequest request = WebRequest.Create(reqUrl) as HttpWebRequest;
                request.Method = method.ToUpperInvariant();

                if (!string.IsNullOrEmpty(token) && token.Length > 1) { request.Headers.Add("Authorization", "Bearer " + token); }
                if (request.Method.ToString() != "GET" && !string.IsNullOrEmpty(paramData) && paramData.Length > 0)
                {
                    request.ContentType = "application/x-www-form-urlencoded";
                    byte[] buffer = Encoding.UTF8.GetBytes(paramData);
                    request.ContentLength = buffer.Length;
                    request.GetRequestStream().Write(buffer, 0, buffer.Length);
                }

                using (HttpWebResponse resp = request.GetResponse() as HttpWebResponse)
                {
                    using (StreamReader stream = new StreamReader(resp.GetResponseStream(), Encoding.UTF8))
                    {
                        string result = stream.ReadToEnd();
                        return result;
                    }
                }
            }
            catch(Exception ex) { return ex.ToString(); }
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

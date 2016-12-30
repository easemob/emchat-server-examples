# demo-server-nodejs
The project is an server-side example code that call Hyphenate's REST API using node.js

### 1. Deployment
In project directory, you need to install all the required node.js packages by running 

```bash
npm install
```

### 2. Configuration
The `config.js` in directory `resources` is configuration file, you can config Organization name(org_name), application name(app_name), client_id, client_secret, host(host name), certificate file(ca), and change their values to what you want.

### 3. Project Structure
`request.js` is used to process request and print response, `client.js` provides request with token service by calling `request.js`. All files in directory `hyphenate` by calling `client.js` respectively implement modules includs request token, users, groups, chatrooms, upload and download files, messages, chat history.

### 4. Run
`test.js` provides an examples for all API invocation, you can change the value of variable `i` for running corresponding API.

Run the following command line in terminal to run the test script
```bash
$ node test
```
// This is a demo program for invoking Easemob Rest API with golang.
// version: 0.1 2016-01-8
// author: Eric23
package main

import (
  "fmt"
  "net/http"
  "io/ioutil"
  "io"
  "bytes"
  "encoding/json"
  "strings"
  "strconv"
)

// ===================================================================================
// Rest resource targets
// ===================================================================================
const applicationBaseUrl string = "https://a1.easemob.com/easemob-playground/test1"
const applicationTokenUrl string = applicationBaseUrl + "/token"
const usersUrl string = applicationBaseUrl + "/users"
const userUrl string = usersUrl + "/{username}"

// ===================================================================================
// Sample structs of payload and response
// ===================================================================================
type AdminTokenRequest struct {
	Grant_type string `json:"grant_type"`
	Client_id string `json:"client_id"`
	Client_secret string `json:"client_secret"`
}

type AdminTokenResponse struct {
	Access_token string `json:"access_token"`
	Expires_in int `json:"expires_in"`
	Application string `json:"application"`
}

type CreateUserRequest struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

type GetDataBasicResponse struct {
	Cursor string `json:"cursor"`
	Count int `json:"count"`
}

func main() {
	clientID := "YXA6wDs-MARqEeSO0VcBzaqg5A"
	clientSecret := "YXA6JOMWlLap_YbI_ucz77j-4-mI0JA"

	// Get Access Token
	tokenResponse, err := GetAccessToken(clientID, clientSecret)
	fmt.Println("--------------------RESPONSE--------------------")
  	if err == nil {
		fmt.Println("Access_token:", tokenResponse.Access_token)
		fmt.Println("Expires_in:", tokenResponse.Expires_in)
		fmt.Println("Application:", tokenResponse.Application)
  	} else {
  		fmt.Println("Access Token failed:", err)
  		return
  	}
  	token := tokenResponse.Access_token

  	fmt.Println("")

  	
  	// Create User
  	createUserResponse, err := CreateUser(token, "demo_username", "demo_password")
  	fmt.Println("--------------------RESPONSE--------------------")
  	if err == nil {
		fmt.Println("Create User:", createUserResponse)
  	} else {
  		fmt.Println("Create User failed:", err)
  		return
  	}

  	fmt.Println("")

  	// Get User
  	getUserResponse, err := GetUser(token, "demo_username")
  	fmt.Println("--------------------RESPONSE--------------------")
  	if err == nil {
		fmt.Println("Get User:", getUserResponse)
  	} else {
  		fmt.Println("Get User failed:", err)
  		return
  	}

  	fmt.Println("")

  	// Get Users
  	getUsersResponse, err, pagenation := GetUsers(token, 5, "")
  	fmt.Println("--------------------RESPONSE--------------------")
  	if err == nil {
		fmt.Println("Get Users:", getUsersResponse)
		fmt.Println("Get Users - Cursor:", pagenation.Cursor)
		fmt.Println("Get Users - Count:", pagenation.Count)
  	} else {
  		fmt.Println("Get Users failed:", err)
  		return
  	}

  	fmt.Println("")
}

// ===================================================================================
// Public Methods
// ===================================================================================
func GetAccessToken(clientID string, clientSecret string) (AdminTokenResponse, error) {
	var adminTokenRequest AdminTokenRequest
  	adminTokenRequest.Grant_type = "client_credentials"
	adminTokenRequest.Client_id = clientID
	adminTokenRequest.Client_secret = clientSecret

	b, err := json.Marshal(adminTokenRequest)
	if err != nil {
		return AdminTokenResponse{}, err
	}
	body := bytes.NewBuffer([]byte(b))

	result, err := sendRequest(applicationTokenUrl, body, "POST", "")
	if err != nil {
		return AdminTokenResponse{}, err
	}

	var adminTokenResponse AdminTokenResponse
	json.Unmarshal([]byte(result), &adminTokenResponse)

	return adminTokenResponse, nil
}

func CreateUser(token string, username string, password string) (string, error){
	var createUserRequest CreateUserRequest
	createUserRequest.Username = username
	createUserRequest.Password = password

	b, err := json.Marshal(createUserRequest)
	if err != nil {
		return "", err
	}
	body := bytes.NewBuffer([]byte(b))

	return sendRequest(usersUrl, body, "POST", token)
}

func GetUser(token string, username string) (string, error) {
	url := resolveTemplate(userUrl, "username", username)
	return sendRequest(url, nil, "GET", token)
}

func GetUsers(token string, limit int, cursor string) (string, error, GetDataBasicResponse) {
	url := usersUrl
	if limit > 0 {
		url += ("?limit=" + strconv.Itoa(limit))
	}
	if cursor != "" {
		url += ("&cursor=" + cursor)
	}

	result, err := sendRequest(url, nil, "GET", token)
	if err != nil {
		return "", err, GetDataBasicResponse{}
	}

	var getDataBasicResponse GetDataBasicResponse
	json.Unmarshal([]byte(result), &getDataBasicResponse)

	return result, err, getDataBasicResponse
}

// ===================================================================================
// Private Methods
// ===================================================================================

func resolveTemplate(template string, variable string, value string) (string) {
	return strings.Replace(template, "{" + variable + "}", value, -1 )
}

func sendRequest(url string, body io.Reader, method string, token string) (string, error){
	fmt.Println("--------------------REQUEST--------------------")
	fmt.Println("Request URL:", url)
	fmt.Println("Request Body:", body)

	client := &http.Client{}
	req,err := http.NewRequest(method, url, body)
	if err != nil {
		return "", err
	}
	req.Header.Set("Content-Type", "application/json;charset=utf-8")
	if token != "" {
		req.Header.Set("Authorization", "Bearer " + token)
	}
    
    res, err := client.Do(req)
	result, err := ioutil.ReadAll(res.Body)
	res.Body.Close()
	if err != nil {
		return "", err
	}

	return string(result), nil
}
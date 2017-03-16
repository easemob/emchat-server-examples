/**
 * Easemob REST APIs
 * Easemob Server REST API Swagger is designated to provide better documentation and thorough interfaces for testing. For more details about server implementation, request rate limitation, etc, please visit [Easemob Server Integration](http://docs.easemob.com/im/100serverintegration/10intro).    **Note:**  `org_ID` is the unique ID of the organization created when you first registered [Easemob console](https://console.easemob.com/).                          `app_name` is the unique app ID created when you new application in [Easemob console](https://console.easemob.com/).            `Authorization token` is required for most API requests as part of requesting header in the format `Bearer ${token}`. You can obtain the token via [/{org_name}/{app_name}/token](https://docs.hyphenate.io/docs/server-overview#section-request-authentication-token).                                             
 *
 * OpenAPI spec version: 1.0.2
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.UserName;


/**
 * Group
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-03-16T15:19:01.833+08:00")
public class Group   {
  @SerializedName("groupname")
  private String groupname = null;

  @SerializedName("desc")
  private String desc = null;

  @SerializedName("public")
  private Boolean _public = null;

  @SerializedName("maxusers")
  private Integer maxusers = null;

  @SerializedName("approval")
  private Boolean approval = null;

  @SerializedName("owner")
  private String owner = null;

  @SerializedName("members")
  private UserName members = null;

  public Group groupname(String groupname) {
    this.groupname = groupname;
    return this;
  }

   /**
   * Get groupname
   * @return groupname
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getGroupname() {
    return groupname;
  }

  public void setGroupname(String groupname) {
    this.groupname = groupname;
  }

  public Group desc(String desc) {
    this.desc = desc;
    return this;
  }

   /**
   * Get desc
   * @return desc
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Group _public(Boolean _public) {
    this._public = _public;
    return this;
  }

   /**
   * Get _public
   * @return _public
  **/
  @ApiModelProperty(example = "null", value = "")
  public Boolean getPublic() {
    return _public;
  }

  public void setPublic(Boolean _public) {
    this._public = _public;
  }

  public Group maxusers(Integer maxusers) {
    this.maxusers = maxusers;
    return this;
  }

   /**
   * Get maxusers
   * @return maxusers
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getMaxusers() {
    return maxusers;
  }

  public void setMaxusers(Integer maxusers) {
    this.maxusers = maxusers;
  }

  public Group approval(Boolean approval) {
    this.approval = approval;
    return this;
  }

   /**
   * Get approval
   * @return approval
  **/
  @ApiModelProperty(example = "null", value = "")
  public Boolean getApproval() {
    return approval;
  }

  public void setApproval(Boolean approval) {
    this.approval = approval;
  }

  public Group owner(String owner) {
    this.owner = owner;
    return this;
  }

   /**
   * Get owner
   * @return owner
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public Group members(UserName members) {
    this.members = members;
    return this;
  }

   /**
   * Get members
   * @return members
  **/
  @ApiModelProperty(example = "null", value = "")
  public UserName getMembers() {
    return members;
  }

  public void setMembers(UserName members) {
    this.members = members;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Group group = (Group) o;
    return Objects.equals(this.groupname, group.groupname) &&
        Objects.equals(this.desc, group.desc) &&
        Objects.equals(this._public, group._public) &&
        Objects.equals(this.maxusers, group.maxusers) &&
        Objects.equals(this.approval, group.approval) &&
        Objects.equals(this.owner, group.owner) &&
        Objects.equals(this.members, group.members);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupname, desc, _public, maxusers, approval, owner, members);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Group {\n");
    
    sb.append("    groupname: ").append(toIndentedString(groupname)).append("\n");
    sb.append("    desc: ").append(toIndentedString(desc)).append("\n");
    sb.append("    _public: ").append(toIndentedString(_public)).append("\n");
    sb.append("    maxusers: ").append(toIndentedString(maxusers)).append("\n");
    sb.append("    approval: ").append(toIndentedString(approval)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    members: ").append(toIndentedString(members)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


package pl.karolmichalski.githubrepos.data.models

import com.fasterxml.jackson.annotation.JsonProperty

class User(@JsonProperty("login") val login: String,
		   @JsonProperty("avatar_url") val avatarUrl: String)
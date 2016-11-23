package bookyard.contracts

data class Constants(val loginUrl : String = "http://localhost:8080/Bookyard.server/login",
					 val securedLoginUrl : String = "https://localhost:8443/Bookyard.server/login",
					 val recommendationsUrl : String = "http://localhost:8080/Bookyard.server/recommend",
					 val securedRecommendationsUrl : String = "https://localhost:8443/Bookyard.server/recommend",
					 val JWT_SUBJECT_LOGIN_REQUEST : String = "LoginRequest",
					 val JWT_SUBJECT_ACCESS_TOKEN : String = "AccessToken") {
}
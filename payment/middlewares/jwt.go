package middlewares

import (
	"net/http"

	"github.com/dgrijalva/jwt-go"
	"github.com/gin-gonic/gin"

	"github.com/EDDYCJY/go-gin-example/pkg/e"
	"github.com/EDDYCJY/go-gin-example/pkg/util"
)

func generateToken() string {
	username := "lynn"
	password := "ahua"

	token, err := util.GenerateToken(username, password)
	if err != nil {

	}
	// token
	// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IjAzMmI1YjJjNjA4YmQ0NTU2OGJiZGE0ZDc3NmM3OTE1IiwicGFzc3dvcmQiOiIxYjA3YTFkNzczNTI0M2QzMmJkY2NlYThhYjI3ZGYxNCIsImV4cCI6MTcwMDA1Mzc0OCwiaXNzIjoiZ2luLWJsb2cifQ.T2qQz0X1tNEWpMQp7pdiXEnQF6g-Nua1vQqzHAU82HU
	return token
}

// JWT is jwt middleware
func JWT(c *gin.Context) {
	var code int
	var data interface{}

	code = e.SUCCESS
	// generate token
	// fmt.Println(generateToken())
	token := c.Query("token")
	// fmt.Println(token)
	if token == "" {
		code = e.INVALID_PARAMS
	} else {
		_, err := util.ParseToken(token)
		if err != nil {
			switch err.(*jwt.ValidationError).Errors {
			case jwt.ValidationErrorExpired:
				code = e.ERROR_AUTH_CHECK_TOKEN_TIMEOUT
			default:
				code = e.ERROR_AUTH_CHECK_TOKEN_FAIL
			}
		}
	}

	if code != e.SUCCESS {
		c.JSON(http.StatusUnauthorized, gin.H{
			"code": code,
			"msg":  e.GetMsg(code),
			"data": data,
		})

		c.Abort()
		return
	}

	c.Next()
}

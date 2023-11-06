package admin

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type IndexController struct {
	BaseController
}

func (con IndexController) Index(c *gin.Context) {
	username, _ := c.Get("username") //Get return interface{}
	v, ok := username.(string)       // need type claim
	if !ok {
		c.String(http.StatusOK, "fail", username)
	} else {
		c.String(http.StatusOK, v)
	}
	// con.success(c)
	// c.String(http.StatusOK, "homepage")
}

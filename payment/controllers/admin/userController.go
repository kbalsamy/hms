package admin

import (
	"github.com/gin-gonic/gin"
)

type UserController struct {
	BaseController
}

func (con UserController) Index(c *gin.Context) {
	con.success(c)
	// c.String(http.StatusOK, "user list")
}
func (con UserController) Add(c *gin.Context) {
	con.success(c)
	// c.String(http.StatusOK, "user add")
}
func (con UserController) Edit(c *gin.Context) {
	con.success(c)
	// c.String(http.StatusOK, "user edit")
}

package controllers

import "github.com/gin-gonic/gin"

type UserController struct {
	BaseController
}

func (con UserController) Index(c *gin.Context) {
	c.String(200, "user list")
}
func (con UserController) Add(c *gin.Context) {
	c.String(200, "add user")
}
func (con UserController) Edit(c *gin.Context) {
	c.String(200, "edit user")
}
func (con UserController) Delete(c *gin.Context) {
	c.String(200, "delete user")
}

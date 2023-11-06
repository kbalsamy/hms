package middlewares

import (
	"fmt"
	"time"

	"github.com/gin-gonic/gin"
)

func InitMiddleware(ctx *gin.Context) {
	//check user login
	start := time.Now().UnixNano()
	fmt.Println("check user login")
	ctx.Set("username", "zhangsan")
	ctx.Next()
	// ctx.Abort()
	fmt.Println(ctx.Request.URL)
	end := time.Now().UnixNano()
	fmt.Println(end - start)
	// cCP := ctx.Copy()  // goroutine need copy
	// go func() {
	// 	time.Sleep(5 * time.Second)
	// 	fmt.Println("Done! in path" + cCP.Request.URL.Path)
	// }()
}

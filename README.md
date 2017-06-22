# CustomToolsApp
comtains Log、Security、Http、hotfix plugin

### componentlibrary：常用组件（日志、HTTP请求等）
### MyLog：日志
  API > 9
  支持输入任意类型
  支持可变参数
  支持输出自动格式化xml，json，url，object
  支持自定义格式Parser
  支持输出可跳转的Log函数定位信息
  支持自定义输出样式

### HTTP: 网络库隔离架构
使用方法：
  1、在Application中初始化HttpHelper.init(new VolleyHttpProcessor(getApplicationContext()));   选择使用的请求框架
  2、获取处理器请求数据：
              /**
               * 发送post请求
               */
              HttpHelper.getHttpProcessor().post(url, params, new HttpCallBack<WeatherInfo>() {
                    @Override
                    protected void onSuccess(WeatherInfo result) {
                        txtHttpResult.setText(result.toString());
                    }
                });
 扩展：实现接口IHttpProcessor选择对应的请求框架写一个对应的请求实体类即可
        （类似：librarys\componentlibrary\src\main\java\com\wuwg\component\net\VolleyHttpProcessor.java）
        
### 动画框架：主要是一个组件的自定义轨迹运动动画框架
使用方法：（见：app\src\main\java\com\wuwg\app\module\animator\AnimatorActivity.java）
AnimatorPath  暂时只有三种自定义动画，定位位置、沿直线轨迹运动、沿赛贝尔曲线曲线轨迹运动               
               /**
                * 开始动画
                */
                private void startAnimator(){
                    AnimatorPath path = new AnimatorPath();
                    path.moveTo(200, 200);
                    path.lineTo(0, 200);
                    path.lineTo(0, 600);
                    path.lineTo(600, 600);
                    path.lineTo(600, 0);
                    path.cubicTo(300, 300, 200, -100, 0, 0);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this, "draw", new PathEvaluator(), path.getPoints().toArray());
                    objectAnimator.setDuration(2000);
                    objectAnimator.start();
                    objectAnimator.setRepeatCount(100);
                }
              
             /**
              * 通过反射调用该方法
              */
             public void setDraw(PathPoint pathPoint){
                 if(pathPoint != null){
                     imgAnimator.setTranslationX(pathPoint.mX);
                     imgAnimator.setTranslationY(pathPoint.mY);
                 }
             }


### customlibrary：自定义空间、常用工具类等

### securitylibrary：安全模块，包括热修复、数据加密、数据存储等


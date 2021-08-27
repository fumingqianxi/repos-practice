<template>
  <Layout>
      <Header class="header">
          <Row>
              <Col span="4" offset="1">
                <Icon type="logo-android" size="50"/>
              </Col>
              <Col span="14">
                <Menu mode="horizontal" :active-name="activeName">
                    <MenuItem name="/app" to="/app">应用分析</MenuItem>
                    <MenuItem name="/push" to="/push">推送营销</MenuItem>
                    <MenuItem name="/dev" to="/dev">开发助手</MenuItem>
                    <MenuItem name="/manage" to="/manage">应用管理</MenuItem>
                </Menu>
              </Col>
              <Col span="4">
                 <Row>
                     <Col span="8">
                        <Dropdown>
                            <Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
                            <DropdownMenu slot="list">
                                <DropdownItem>我的设置</DropdownItem>
                                <DropdownItem>我的收藏</DropdownItem>
                                <DropdownItem >设置<Badge status="error" /></DropdownItem>
                                <DropdownItem divided>退出登录</DropdownItem>
                            </DropdownMenu>
                        </Dropdown>
                     </Col>
                     <Col span="8">
                        <Dropdown>
                            <Badge :count="count" :offset="[20,4]">
                            <Icon type="md-notifications-outline" size="24" />
                            </Badge>
                            <DropdownMenu slot="list">
                                <Tabs value="notification">
                                    <TabPane label="通知" name="notification">
                                        <div class="notification">通知内容</div>
                                    </TabPane>
                                    <TabPane label="关注" name="follow">
                                        <div class="notification">关注内容</div>
                                    </TabPane>
                                    <TabPane label="系统" name="system">
                                        <div class="notification">系统内容</div>
                                    </TabPane>
                                </Tabs>
                            </DropdownMenu>
                        </Dropdown>
                     </Col>
                     <Col span="8">
                        <Icon type="md-color-palette" size="24" @click="openTheme = true;"/>
                     </Col>
                 </Row>
              </Col>
          </Row>
      </Header>
      <Layout>
          <Sider class="sider" width="240" collapsible v-model="isCollapsed">
              <Menu theme="dark" class="sider-menu" :active-name="option1" :class="{ 'sider-hide': isCollapsed }">
                <MenuItem name="option1">
                    <Icon type="ios-search"></Icon>
                    <span> Option 1</span> 
                </MenuItem>
                <MenuItem name="option2">
                    <Icon type="ios-apps"></Icon>
                    <span> Option 2</span>
                </MenuItem>
                <MenuItem name="option3">
                    <Icon type="ios-bookmark"></Icon>
                    <span> Option 3</span>
                </MenuItem>
            </Menu>
          </Sider>
          <Content class="content" :class="{ 'content-expand': isCollapsed }">
                <slot></slot>
          </Content>
      </Layout>

      <Drawer title="选择配色" v-model="openTheme" :closable="false">

      </Drawer>
  </Layout>
</template>

<script>
export default {
    data() {
        return {
            activeName: this.$route.path,
            count: 2,
            openTheme: false,
            isCollapsed: false
        };
    },
    created() {
        // this.activeName = this.$route.path;
    }
}
</script>

<style>
    .header {
        width: 100%;
        height: 60px;
        background-color: #fff;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
        position: fixed;
        top: 0;
        left: 0;
        z-index: 2;
    }
    .notification {
        text-align: center;
        height: 200px;
    }
    .ivu-menu-horizontal.ivu-menu-light:after {
        display: none;
    }
    .sider {
        position: fixed;
        height: 100%;
        left: 0;
        overflow: auto;
        z-index: 1;
    }
    .sider-menu {
        margin-top: 60px;
    }
    .sider-hide .ivu-menu-item span {
        display: none;
    }
    .content {
        margin-left: 240px;
        margin-top: 60px;
        padding: 16px;
        transition: all .2s ease-in-out;
    }
    .content-expand {
        margin-left: 64px;
    }
</style>
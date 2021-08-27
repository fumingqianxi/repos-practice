<template>
  <dev-article>
    <Card :padding="0">
      <Row>
        <Col :sm="24" :md="0">
          <Tabs value="option1">
            <TabPane label="设置1" name="option1"></TabPane>
            <TabPane label="设置2" name="option2"></TabPane>
            <TabPane label="设置3" name="option3"></TabPane>
          </Tabs>
        </Col>
      </Row>
      <Row>
        <Col :sm="0" :md="4">
          <CellGroup>
            <Cell title="设置1" to="/manage" />
            <Cell title="设置2" to="/manage" />
            <Cell title="设置3" to="/manage" />
          </CellGroup>
        </Col>
        <Col :sm="24" :md="20">
          <Row style="padding: 16px" :gutter="64">
            <Col span="12">
              <Form lable-position="top">
                <FormItem label="邮箱">
                  <Input v-model="form.mail"></Input>
                </FormItem>
                <FormItem label="昵称">
                  <Input v-model="form.name"></Input>
                </FormItem>
                <FormItem label="个人介绍">
                  <Input v-model="form.desc" type="textarea" :rows="4"></Input>
                </FormItem>
                <FormItem label="国家">
                  <Select v-model="form.country">
                    <Option value="中国">中国</Option>
                    <Option value="美国">美国</Option>
                  </Select>
                </FormItem>
                <FormItem label="电话">
                  <Row :gutter="16">
                    <Col span="6"><Input v-model="form.zone"></Input></Col>
                    <Col span="18"><Input v-model="form.phone"></Input></Col>
                  </Row>
                </FormItem>
              </Form>
              <Button type="primary" size="large">更新信息</Button>
            </Col>
            <Col span="12">
              <Avatar icon="ios-person" size="large" />
              <Upload action="//jsonplaceholder.typicode.com/posts/" style="margin-top: 10px">
                <Button icon="ios-cloud-upload-outline">上传头像</Button>
              </Upload>
            </Col>
          </Row>
        </Col>
      </Row>
    </Card>
  </dev-article>
</template>

<script>
export default {
  data() {
    return {
      form: {
        mail: '',
        name: '',
        desc: '',
        country: '',
        zone: '010',
        phone: '1000000'
      }
    }
  }
}
</script>

<style>

</style>
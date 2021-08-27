<template>
  <dev-article>
    <Row :gutter="16">
      <Col span="6">
        <Card title="访问量">
          <Tag color="green" slot="extra">周</Tag>
          <div class="count">123,000</div>
          <Divider></Divider>
          日访问量 1,230
        </Card>
      </Col>
      <Col span="6">
        <Card title="总销售额">
          <Tooltip content="这里是指标说明" slot="extra" placement="top" transfer>
            <Icon type="ios-alert-outline" size="18" />
          </Tooltip>
          <div class="count">￥880,000</div>
          <Divider></Divider>
          周同比 12%<Icon type="md-arrow-dropup" color="red" />日环比 10%<Icon type="md-arrow-dropdown" color="green" />
        </Card>
      </Col>
      <Col span="6">
        <Card title="运营活动效果">
          <Tooltip content="这里是指标说明" slot="extra" placement="top" transfer>
            <Icon type="ios-alert-outline" size="18" />
          </Tooltip>
          <div class="count">75%</div>
          <Divider></Divider>
          <Progress :percent="75" status="success" hide-info />
        </Card>
      </Col>
      <Col span="6">
        <Card title="快捷操作">
          <Row>
            <Col v-for="item in shortcuts" :key="item.title" span="8">
              <Button :to="item.action">{{ item.title }}</Button>
            </Col>
          </Row>
          <Divider></Divider>
          <Button type="primary" icon="md-add" long @click="newShortcut.status = true">添加</Button>
        </Card>
      </Col>
    </Row>

    <Card style="margin-top: 16px">
      <Tabs value="sell">
        <Tempalte slot="extra">
          <RadioGroup v-model="dateType" @on-change="handleSetDate">
            <Radio label="day" ></Radio>
            <Radio label="week"></Radio>
            <Radio label="month"></Radio>
            <Radio label="year"></Radio>
          </RadioGroup>
          <DatePicker type="daterange" v-model="countDate" style="width: 250px;" transfer></DatePicker>
        </Tempalte>
        <TabPane label="销售额" name="sell">
          <Row>
            <Col span="18">
              <div style="width: 100%;height: 400px" ref="chart">

              </div>
            </Col>
            <Col span="6">
              <Table :columns="columns1" :data="data1"></Table>
            </Col>
          </Row>
        </TabPane>
        <TabPane label="访问量" name="visit"></TabPane>
    </Tabs>
    </Card>

    <Modal title="添加快捷操作" v-model="newShortcut.status" @on-ok="handleAddShortcut">
      <Input v-model="newShortcut.title" placeholder="快捷操作标题" />
      <Input v-model="newShortcut.action" placeholder="快捷操作链接" />
    </Modal>
  </dev-article>
</template>

<script>
export default {
  data() {
    return {
      shortcuts: [
        {
          title: '操作一',
          action: '/app'
        },
        {
          title: '操作二',
          action: '/push'
        }
      ],
      newShortcut: {
        status: false,
        title: '',
        action: ''
      },
      dateType: 'day',
      countDate: [new Date(), new Date()],
      columns1: [
        {
            title: 'Name',
            key: 'name'
        },
        {
            title: 'Age',
            key: 'age'
        },
        {
            title: 'Address',
            key: 'address'
        }
      ],
      data1: [
        {
            name: 'John Brown',
            age: 18,
            address: 'New York No. 1 Lake Park',
            date: '2016-10-03'
        },
        {
            name: 'Jim Green',
            age: 24,
            address: 'London No. 1 Lake Park',
            date: '2016-10-01'
        },
        {
            name: 'Joe Black',
            age: 30,
            address: 'Sydney No. 1 Lake Park',
            date: '2016-10-02'
        },
        {
            name: 'Jon Snow',
            age: 26,
            address: 'Ottawa No. 2 Lake Park',
            date: '2016-10-04'
        },
        {
          name: 'Xiao Ming',
          age: 24,
          address: 'shandong',
          date: '2021-8-25'
        }
      ]
    };
  },
  methods: {
    handleAddShortcut() {
      this.shortcuts.push({
        title: this.newShortcut.title,
        action: this.newShortcut.action
      });
    },
    handleSetDate(type) {
      let today = new Date().getTime();
      let date;
      switch(type) {
        case 'day':
          date = today;
          break;
        case 'week':
          date = today - 86400000 * 7;
          break;
        case 'month':
          date = today - 86400000 * 30;
          break;
        case 'year':
          date = today - 86400000 * 365;
          break;
      }
      this.countDate = [new Date(date), new Date(today)];
    },
    mounted() {
      
    }
  }
}
</script>
  
<style>
  .count {
    font-size: 24px;
  }
</style>
<template>
  <div style="padding: 32px 64px">
      <h1>服务端分页及自定义序号</h1>
      <Table :columns="columns" :data="data" :loading="loading" border size="small"></Table>

      <div style="text-align: center;margin: 16px 0">
          <Page :total="total" :current.sync="current" show-sizer @on-change="getData"></Page>
      </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            columns: [

            ],
            data: [

            ],
            loading: false,
            total: 0,
            current: 1
        };
    },
    methods: {
        getData() {
            
        }
    }
}
</script>

<style>

</style>
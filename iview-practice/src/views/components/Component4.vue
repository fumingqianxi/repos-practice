<template>
    <div style="padding: 32px 500px">
        <h1>Tabs标签页关闭前二次确认</h1>
        <br>

        <Tabs type="card" closable @on-tab-remove="handleTabRemove">
            <TabPane label="标签一" v-show="tab0" >标签一的内容</TabPane>
            <TabPane label="标签二" v-show="tab1">标签二的内容</TabPane>
            <TabPane label="标签三" v-show="tab2">标签三的内容</TabPane>
        </Tabs>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tab0: true,
                tab1: true,
                tab2: true
            }
        },
        methods: {
            handleTabRemove(name) {
                this['tab' + name] = false;
            }
        }
    }
</script>

<style>

</style>
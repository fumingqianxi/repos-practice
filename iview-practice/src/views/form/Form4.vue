<template>
    <div style="padding: 32px 500px;">
        <h1>Form表单组件的联动用法</h1>
        <br>

        <Form ref="form" :model="form" :label-width="70">
            <FormItem label="信息">
                <Input v-model="form.message" />
            </FormItem>

            <FormItem label="性别">
                <Select v-model="form.gender" @on-change="handleChangeGender">
                    <Option value="male">男性</Option>
                    <Option value="female">女性</Option>
                </Select>
            </FormItem>

            <FormItem label="城市">
                <Select v-model="form.city" @on-change="handleChangeCity" style="width: 150px;margin-right: 20px">
                    <Option value="beijing">北京</Option>
                    <Option value="shanghai">上海</Option>
                </Select>

                <Select v-model="form.area" v-if="form.city !== ''" style="width: 150px;">
                    <template v-if="form.city === 'beijing'">
                        <Option value="chaoyang">朝阳</Option>
                        <Option value="haidian">海淀</Option>
                    </template>
                    <template v-if="form.city === 'shanghai'">
                        <Option value="pudong">浦东</Option>
                        <Option value="xuhui">徐汇</Option>
                    </template>
                </Select>
            </FormItem>
        </Form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            form: {
                message: '',
                gender: '',
                city: '',
                area: ''
            }
        }
    },
    methods: {
        handleChangeGender(val) {
            if (val === 'male') {
                this.form.message = '你好，先生！';
            } else {
                this.form.message = '你好，女士！';
            }
        },
        handleChangeCity(val) {
            if (val === 'beijing') {
                this.form.area = "chaoyang";
            } else {
                this.form.area = "pudong";
            }
        }
    }
}
</script>

<style>

</style>
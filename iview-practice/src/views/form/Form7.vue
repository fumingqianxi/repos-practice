<template>
    <div style="padding: 32px 500px;">
        <h1>巧用元素的禁用属性</h1>
        <br>

        <Form ref="form" :model="form" :label-width="70">
            <FormItem label="姓名">
                <Input v-model="form.name" />
            </FormItem>

            <FormItem label="密码">
                <Input v-model="form.password" type="password" />
            </FormItem>

            <FormItem label="确认密码">
                <Input v-model="form.password2" type="password" />
            </FormItem>

            <Button :disabled="submitDisabled">提交</Button>
        </Form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            form: {
                name: '',
                password: '',
                password2: ''
            }
        }
    },
    computed: {
        submitDisabled() {
            let disabled = false;
            if (this.form.name === '') disabled = true;
            if (this.form.password.length < 6) disabled = true;
            if (this.form.password !== this.form.password2) disabled = true;
            return disabled;
        }
    }
}
</script>

<style>

</style>
<template>
    <div style="padding: 32px 500px">
        <h1>Form 表单验证的常用验证规则讲解</h1>
        <br>

        <Form ref="form" :model="form" :rules="rules" :label-width="70">
            <FormItem label="输入框" prop="input">
                <Input v-model="form.input" />
            </FormItem>

            <Button @click="handleSubmit">提交</Button>
        </Form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            form: {
                input: ''
            },
            rules: {
                input: [
                    {
                        required: true,
                        trigger: 'change',
                        type: 'string',
                        min: 2,
                        max: 5
                    }
                ]
            }
        }
    },
    methods: {
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    this.$Message.success('成功');
                } else {
                    this.$Message.error('失败');
                }
            });
        }
    }
}
</script>

<style>

</style>
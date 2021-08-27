<template>
    <div style="padding: 32px 500px">
        <h1>设置表单验证规则的多种方式</h1>
        <br>

        <Form ref="form" :model="form" :rules="rules" :label-width="70">
            <RadioGroup v-model="type" style="margin: 20px">
                <Radio :label="1">表单1</Radio>
                <Radio :label="2">表单2</Radio>
            </RadioGroup>

            <FormItem label="表单1" prop="input1" :required="required" v-if="type === 1">
                <Input v-model="form.input1" />
            </FormItem>

            <FormItem label="表单2" prop="input2" v-if="type === 2">
                <Select v-model="form.input2">
                    <Option value="value1">value1</Option>
                    <Option value="value2">value2</Option>
                </Select>
            </FormItem>
            <Button @click="handleSubmit">提交</Button>
        </Form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            required: true,
            type: 1,
            form: {
                input1: '',
                input2: ''
            },
            rules: {
                input1: [
                    {
                        // required: true
                    }
                ],
                input2: [
                    {
                        required: true
                    }
                ],
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
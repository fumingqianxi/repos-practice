<template>
    <div style="padding: 32px 64px">
        <TodoList :list="list">
            <template slot-scope="props">
                <span v-if="props.todo.checked">√</span>
                - {{ props.todo.text }}
            </template>
        </TodoList>
    </div>
</template>

<script>
import TodoList from '../../components/todo-list.vue';
export default {
    components: {
        TodoList
    },
    data() {
        return {
            list: [
                {
                    id: 1,
                    text: '选项1',
                    checked: false
                },
                {
                    id: 2,
                    text: '选项2',
                    checked: true
                }
            ]
        }
    }
}
</script>

<style>

</style>
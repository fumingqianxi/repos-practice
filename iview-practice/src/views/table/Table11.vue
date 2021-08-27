<template>
    <div style="padding: 32px 64px">
        <h1>自定义列模板的三种方式</h1>

        <Table :columns="columns" :data="data" border ref="table">
            <template slot-scope="p" slot="action">
                <Select>
                    <Option :value="p.name">{{ p.name }}</Option>
                    <Option :value="p.age">{{ p.age }}</Option>
                    <Option :value="p.address">{{ p.address }}</Option>
                    <Option :value="p.date">{{ p.date }}</Option>
                </Select>
            </template>
        </Table>
    </div>
</template>

<script>
// import TableCell from '../../components/table-cell.vue';
export default {
    data() {
        return {
            columns: [
                {
                    title: '姓名',
                    key: 'name'
                },
                {
                    title: '年龄',
                    key: 'age'
                },
                {
                    title: '地址',
                    key: 'address'
                },
                {
                    title: '日期',
                    key: 'date'
                },
                {
                    title: '操作',
                    render: (h, { row }) => {
                        // return h('Select', [
                        //     h('Option', {
                        //         props: {
                        //             value: row.name
                        //         }
                        //     }, row.name),
                        //     h('Option', {
                        //         props: {
                        //             value: row.age
                        //         }
                        //     }, row.age),
                        //     h('Option', {
                        //         props: {
                        //             value: row.address
                        //         }
                        //     }, row.address),
                        //     h('Option', {
                        //         props: {
                        //             value: row.date
                        //         }
                        //     }, row.date)
                        // ]);

                        // return h(TableCell, {
                        //     props: {
                        //         row
                        //     }
                        // })

                        return h(
                            'div',
                            this.$refs.table.$scopedSlots.action({
                                name: row.name,
                                age: row.age,
                                address: row.address,
                                date: row.date
                            })
                        )
                    }
                }
            ],
            data: [
                
            ]
        }
    },
    mounted() {
        this.data = [
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
            }
        ]
    }
}
</script>

<style>

</style>
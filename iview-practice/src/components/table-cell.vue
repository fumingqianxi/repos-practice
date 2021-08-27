<template>
    <Select transfer>
        <Option>请选择</Option>
        <Option :value="row.name">{{ row.name }}</Option>
        <Option :value="row.age">{{ row.age }}</Option>
        <Option :value="row.address">{{ row.address }}</Option>
        <Option :value="row.date">{{ row.date }}</Option>
    </Select>
</template>

<script>
export default {
    props: {
        row: Object
    }
}
</script>

<style>

</style>
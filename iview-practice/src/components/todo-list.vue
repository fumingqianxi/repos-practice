<template>
    <ul>
        <li v-for="item in list" :key="item.id">
            <slot :todo="item">
                {{ item.text }}
            </slot>
        </li>
    </ul>
</template>

<script>
export default {
    props: {
        list: {
            type: Array
        }
    }
}
</script>

<style>

</style>
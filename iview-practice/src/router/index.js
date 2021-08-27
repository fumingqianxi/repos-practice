import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/app',
    component: () => import(/* webpackChunkName: "about" */ '../views/layout/App.vue')
  },
  {
    path: '/push',
    component: () => import(/* webpackChunkName: "about" */ '../views/layout/Push.vue')
  },
  {
    path: '/dev',
    component: () => import(/* webpackChunkName: "about" */ '../views/layout/Dev.vue')
  },
  {
    path: '/manage',
    component: () => import(/* webpackChunkName: "about" */ '../views/layout/Manage.vue')
  },
  {
    path: '/table1',
    component: () => import(/* webpackChunkName: "about" */ '../views/table/Table1.vue')
  },
  {
    path: '/table2',
    component: () => import(/* webpackChunkName: "about" */ '../views/table/Table2.vue')
  },
  {
    path: '/table3',
    component: () => import(/* webpackChunkName: "about" */ '../views/table/Table3.vue')
  },
  {
    path: '/table4',
    component: () => import(/* webpackChunkName: "about" */ '../views/table/Table4.vue')
  },
  {
    path: '/table5',
    component: () => import(/* webpackChunkName: "about" */ '../views/table/Table5.vue')
  },
  {
    path: '/table11',
    component: () => import(/* webpackChunkName: "about" */ '../views/table/Table11.vue')
  },
  {
    path: '/table12',
    component: () => import(/* webpackChunkName: "about" */ '../views/table/Table12.vue')
  },
  {
    path: '/form1',
    component: () => import(/* webpackChunkName: "about" */ '../views/form/Form1.vue')
  },
  {
    path: '/form4',
    component: () => import(/* webpackChunkName: "about" */ '../views/form/Form4.vue')
  },
  {
    path: '/form2',
    component: () => import(/* webpackChunkName: "about" */ '../views/form/Form2.vue')
  },
  {
    path: '/form7',
    component: () => import(/* webpackChunkName: "about" */ '../views/form/Form7.vue')
  },
  {
    path: '/form8',
    component: () => import(/* webpackChunkName: "about" */ '../views/form/Form8.vue')
  },
  {
    path: '/component4',
    component: () => import(/* webpackChunkName: "about" */ '../views/components/Component4.vue')
  },
  {
    path: '/component5',
    component: () => import(/* webpackChunkName: "about" */ '../views/components/Component5.vue')
  },
  {
    path: '/',
    redirect: '/app'
  }
]

const router = new VueRouter({
  routes
})

export default router

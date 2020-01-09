import Vue from 'vue'
import Router from 'vue-router';
import Home from '../components/Home';

import Members from '../components/Members'
import LoginAdmin from '../components/LoginAdmin'
import LoginEmployee from '../components/LoginEmployee'
import Adminmenu from '../components/Adminmenu'
import Employeemenu from '../components/Employeemenu'
// import Reservation from '../components/Reservation'
import Borrow from '../components/Borrow'
import Returns from '../components/Returns'
import Sportequipment from '../components/Sportequipment'
import Employee from '../components/Employee'
Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            component: Home
        },
        {
            path: '/LoginAdmin',
            component: LoginAdmin
        },
        {
            path: '/LoginEmployee',
            component: LoginEmployee
        },
        {
            path: '/Adminmenu',
            component: Adminmenu
        },
        {
            path: '/Adminmenu/Employee',
            component: Employee
        },
        {
            path: '/Employeemenu',
            component: Employeemenu
        },
        {
            path: '/Employeemenu/Members',
            component: Members
        },
        // {
        //     path: '/Employeemenu/Reservation',
        //     component: Reservation
        // },
        {
            path: '/Employeemenu/Borrow',
            component: Borrow
        },
        {
            path: '/Employeemenu/Returns',
            component: Returns
        },
        {
            path: '/Employeemenu/Sportequipment',
            component: Sportequipment
        },

    ]
});
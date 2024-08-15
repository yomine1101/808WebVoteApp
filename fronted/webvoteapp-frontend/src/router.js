import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from './components/LoginPage.vue';
import AdminPage from './components/AdminPage.vue';
import UserPage from './components/UserPage.vue';

const routes = [
    { path: '/', redirect: '/login' },
    { path: '/login', name: 'LoginPage', component: LoginPage },
    { path: '/admin', name: 'AdminPage', component: AdminPage },
    { path: '/user', name: 'UserPage', component: UserPage },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;

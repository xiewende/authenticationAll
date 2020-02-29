/**
 * 路由器模块
 */
import Vue from 'vue'
import VueRouter from 'vue-router'

import Teacher from '../pages/Teacher.vue'
import Course from '../pages/Course.vue'
import Student from '../pages/Student.vue'
import Selcourse from '../pages/Selcourse.vue'
import Indication from '../pages/Indication.vue'
import Target from '../pages/Target.vue'
import IndicationCourse from '../pages/IndicationCourse.vue'
import Exam from '../pages/Exam.vue'
import ExamDetail from '../pages/ExamDetail.vue'
import Login from '../pages/Login.vue'
import CourseAchievement from '../pages/CourseAchievement.vue'
import Score from '../pages/Score.vue'
import GraduateIndividual from '../pages/GraduateIndividual.vue'
import GraduateRequirement from '../pages/GraduateRequirement.vue'

Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/teacher',
            component: Teacher,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/course',
            component: Course,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/student',
            component: Student,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/selcourse',
            component: Selcourse,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/indication',
            component: Indication,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/target',
            component: Target,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/indicationcourse',
            component: IndicationCourse,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/exam',
            component: Exam,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/examdetail',
            component: ExamDetail,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/courseAchievement',
            component: CourseAchievement,
            meta: {
                showGuide: true,
                showHeader: true
            }
        },
        {
            path:'/score',
            component:Score,
            meta:{
                showGuide: true,
                showHeader: true
            }
        },
        {
            path:'/graduateIndividual',
            component:GraduateIndividual,
            meta:{
                showGuide: true,
                showHeader: true
            }
        },
        {
            path:'/graduateRequirement',
            component:GraduateRequirement,
            meta:{
                showGuide: true,
                showHeader: true
            }
        },
        {
            path: '/login',
            component: Login,
        }
    ]
})
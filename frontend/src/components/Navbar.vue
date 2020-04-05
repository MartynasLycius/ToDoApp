
<template>
   <div>
       <v-navigation-drawer  v-model="drawer"  app clipped >

           <v-list dense nav >
               <v-list-item  v-for="menu in menus" :key="menu.title"  link router :to="menu.route">
                   <v-list-item-icon>
                       <v-icon>{{ menu.icon }}</v-icon>
                   </v-list-item-icon>

                   <v-list-item-content>
                       <v-list-item-title>{{ menu.title }}</v-list-item-title>
                   </v-list-item-content>
               </v-list-item>
           </v-list>

       </v-navigation-drawer>

       <v-app-bar  app clipped-left>
           <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
           <v-toolbar-title class="text-uppercase grey--text">
               <span class="font-weight-light">Todo</span>
               <span>App</span>
           </v-toolbar-title>
       </v-app-bar>

   </div>
</template>

<script>
    export default {
        data() {
            return {
                drawer: true,
            }
        },
        computed: {
            menus () {
                return this.$store.getters.getMenus;
            }
        }
    }
</script>

<style>
</style>
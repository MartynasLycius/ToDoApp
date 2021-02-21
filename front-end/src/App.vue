<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://www.proit.lt/html/media/images/proit.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://www.eastnetic.com/t"
        target="_blank"
        text
      >
        <span class="mr-2">EastNetic</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>

    </v-app-bar>

    <v-main>
      <Todo/>
    </v-main>
  </v-app>
</template>

<script>
import Todo from './components/Todo';

export default {
  name: 'App',

  components: {
    // eslint-disable-next-line vue/no-unused-components
    Todo,
  },

  data: () => ({
    //
  }),
};
</script>

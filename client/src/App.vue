<template>
  <v-app style = "background: #ffffcc;">

    <v-app-bar app style = "background: #ffbe33;">
      <v-toolbar-title class="headline">
        <h3 class="center">สถานกีฬา</h3>
      </v-toolbar-title>
      <v-spacer></v-spacer>
    
        <v-btn  class="ma-2 right" color="primary" dark @click="home">HOME
          <v-icon dark >mdi-home</v-icon>
        </v-btn>
      
    </v-app-bar>

    <v-content>
      <!-- <VideoRental/> -->
      <router-view />

    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data: () => ({
    
  }),

  methods: {
    home(){
    this.$router.push("/");
  }
}

  
};
</script>

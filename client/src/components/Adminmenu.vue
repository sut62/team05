<template>
<v-card class="mx-auto" height="100%" width="100%">
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >
  <v-container>
    <br />
    <br />
    <br />
    <br />

    <v-card max-width="700" class="mx-auto">
      <v-system-bar color="green"></v-system-bar>
      <v-system-bar color="green"></v-system-bar>
      <v-row justify="center">
        <v-toolbar-title>
          <h1>ยินดีต้อนรับ</h1>
        </v-toolbar-title>
      </v-row>
      <v-row justify="center">
        <v-col>
          <v-row justify="center">
            <v-btn style="width:180px;height:60px " color="green" @click="aa">
              <font color="#FFFFFF">สมัครพนักงาน</font>
            </v-btn>
          </v-row>
        </v-col>
        <!-- <v-col>
          <v-row justify="center">
            <v-btn style="width:180px;height:60px" color="pink" @click="bb">
              <font color="#FFFFFF">นัดสัมภาษณ์งาน</font>
            </v-btn>
          </v-row>
        </v-col>-->
      </v-row>

      <br />
      <br />
      <v-system-bar color="green"></v-system-bar>
      <v-system-bar color="green"></v-system-bar>
    </v-card>
  </v-container>
  </v-navigation-drawer>
    </v-card>
</template>



<script>
export default {
  methods: {
    aa() {
      this.$router.push("/Adminmenu/Employee");
    },
    // bb() {
    //   this.$router.push("/registercompany/login/Interview");
    // }
  }
};
</script>

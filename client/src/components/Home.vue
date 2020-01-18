
<template>
  <div>
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >
    <v-row align="center">
      <v-col class="text-center">
        <font color="white">
          <p class="text-center font-weight-black display-4">ยินดีต้อนรับ</p>
        </font>
        <!-- <font color="white" size = "1000">ยินดีต้อนรับ</font> -->
        <p></p>
      </v-col>
    </v-row>

    <v-row align="center">
      <v-col class="text-center">
        <v-card max-width="600" height="450" class="mx-auto" color="#F5F5F5" elevation="20">
          <v-row align="center">
            <v-col class="text-center">
              <p></p>
              <font color="black">
                <p class="text-center font-weight-black display-2">เจ้าของสถานกีฬา</p>
              </font>
              <p></p>
              <br />
              <img src="..\assets\user.png" style="width:200px;height:200px;" />
            </v-col>
          </v-row>
          <v-row align="center">
            <v-col class="text-center" cols="30">
              <v-btn x-large color="green" dark @click="adminlogin">ล็อกอิน</v-btn>
            </v-col>
          </v-row>
        </v-card>
      </v-col>

      <v-col class="text-center">
        <v-card max-width="600" height="450" class="mx-auto" color="#F5F5F5" elevation="20">
          <v-row align="center">
            <v-col class="text-center">
              <p></p>
              <font color="black">
                <p class="text-center font-weight-black display-2">พนักงาน</p>
              </font>
              <p></p>
              <br />
              <img src="..\assets\sport.png" style="width:200px;height:200px;" />
            </v-col>
          </v-row>

          <v-row align="center">
            <!-- <v-col class="text-center" cols="30"> 
                        <v-btn x-large color="green" dark @click="userlogin">ผู้สมัครงาน</v-btn>
            </v-col>-->
            <v-col class="text-center" cols="30">
              <v-btn x-large color="pink" dark @click="employeelogin">ล็อกอิน</v-btn>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    </v-navigation-drawer>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  data() {
    return {};
  },

  methods: {
    adminlogin() {
      this.$router.push("/LoginAdmin");
    },
    employeelogin() {
      this.$router.push("/LoginEmployee");
    }
  }

};
</script>

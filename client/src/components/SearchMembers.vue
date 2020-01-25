<template>
  <v-card class="mx-auto" height="100%" width="100%">
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >
    <br>
      <v-card class="mx-auto" max-width="850" color="#8B636C">
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-system-bar color="#CD919E"></v-system-bar>

        <v-row justify="center">
          <v-toolbar-title>
            <h1>ค้นหาข้อมูลผู้ใช้งาน</h1>
          </v-toolbar-title>
        </v-row>
        <v-content>
          <v-card max-width="600" class="mx-auto" color="ffffff">
            <v-container fluid>
              <v-col cols="10">
                <v-text-field
                  outlined
                  label="User Name"
                  v-model="member_username"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
                
                <p v-if="CheckID != ''" >ชื่อผู้ใช้ : {{nametype}}  {{name}}</p>
                <p v-if="CheckID != ''">เพศ : {{gender}}</p>
                <p v-if="CheckID != ''">ปี-เดือน-วันเกิด : {{date}}</p>
                <p v-if="CheckID != ''">ที่อยู่ : {{address}}   {{province}}</p>
                <p v-if="CheckID != ''">Email : {{email}}</p>
                <p v-if="CheckID != ''">เบอร์โทรศัพท์ :  {{phonenumber}}</p>
              </v-col>
               <div v-if="alert1 == 'null'"></div>
            <div v-else-if="alert1 == 'true'">
              <v-alert type="success">พบผู้ใช้งาน</v-alert>
            </div>
            <div v-else-if="alert1 == 'false'">
              <v-alert type="error">ไม่พบผู้ใช้งาน</v-alert>
            </div>
              <v-col cols="2">
                <div class="my-2">
                  <v-btn @click="ShowMemberId" depressed large color="primary">Search</v-btn>
                </div>
                </v-col>
            </v-container>
            <v-col cols="3">
      <v-btn x-medium color="#6C7B8B" style="margin-left: 380%;" dark @click="back">Back</v-btn>
    </v-col>
            </v-card>
            <br>
</v-content>
<v-system-bar color=#CD919E></v-system-bar>
<v-system-bar color=#CD919E></v-system-bar>
              </v-card>
</v-navigation-drawer>
</v-card>
</template>

<script>
import http from "../http-common";
export default {
  data() {
    return {
      alert: "null",
      alert1: "null",
      member_username: null,
      member_id: null,
      CheckID: false,
      nametype: "",
      name: "", 
      date:"",
      gender : "",
      address :"",
      province :"",
      email:"",
      phonenumber:"",
    };
  },
  methods: {
    back() {
      this.$router.push("/Employeemenu");
    },
    ShowMemberId() {
      http
        .get("/Members/" + this.member_username)
        .then(response => {
          console.log(response);
          // console.log(JSON.parse(JSON.stringify(response.data)));
          if (response.data != []) {
            this.member_id = response.data.member_id;
            this.nametype = response.data.nametype.nametype;
            this.name = response.data.name;
            this.gender = response.data.gender.gender;
            this.date = response.data.date;
            this.address = response.data.address;
            this.province = response.data.province.province;
            this.email = response.data.email;
            this.phonenumber = response.data.phonenumber;

            this.CheckID = response.status;
            this.alert1 = "true";
            // alert("พบผู้ใช้งาน");
          } else {
            this.member_id1 = null;
            this.alert1 = "false";
            // alert("ไม่พบผู้ใช้งาน");
          }
        })
        .catch(e => {
          console.log(e);
        });
    },

  },
};
</script>
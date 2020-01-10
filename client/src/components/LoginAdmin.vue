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
    <v-card max-width="700" class="mx-auto">
      <v-system-bar color="green"></v-system-bar>
      <v-system-bar color="green"></v-system-bar>
      <v-row justify="center">
        <v-toolbar-title>
          <h1>Login</h1>
        </v-toolbar-title>
      </v-row>
      <v-row justify="center">
        <v-col cols="5">
          <v-text-field
            solo
            label="Email"
            v-model="email"
            :rules="[(v) => !!v || 'This field is required']"
            required
            clearable
            prepend-icon="mdi-email"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col cols="5">
          <v-text-field
            solo
            label="PASSWORD"
            v-model="password"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show ? 'text' : 'password'"
            prepend-icon="mdi-lock"
            required
            counter
            clearable
            @click:append="show = !show"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col>
          <v-btn style="margin-left:43%;" @click="BLogin">Login</v-btn>

          <!-- <v-btn 
                style="margin-left:25%;"
                @click="Register">Register
                 
          </v-btn>-->
        </v-col>
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
import http from "../http-common";
export default {
  name: "Loing",
  data() {
    return {
      email: "",
      password: "",
      show: false
    };
  },
  methods: {
    getUsers() {
      http
        .get("/user")
        .then(response => {
          this.users = response.data;

          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    BLogin() {
      http
        .get("/check/" + this.email + "/" + this.password)
        .then(response => {
          console.log(response);
          if (response.data[0] != null) {
            localStorage.setItem("email", response.data[0].email);
            localStorage.setItem("name", response.data[0].name);
            localStorage.setItem("id", response.data[0].id);
            localStorage.setItem("phone", response.data[0].phone);
            localStorage.setItem("nametype", response.data[0].nametype);
            this.$router.push("/Adminmenu");
          } else {
            alert("รหัสผ่านไม่ถูกต้อง");
          }
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>

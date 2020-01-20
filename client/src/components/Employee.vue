
<template>
  <v-card class="mx-auto" height="100%" width="100%">
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >
      <br />
      <v-card class="mx-auto" max-width="850" color="#8B636C">
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-row justify="center">
          <v-toolbar-title>
            <h1>สมัครพนักงาน</h1>
          </v-toolbar-title>
        </v-row>
        <v-row justify="center">
          <v-col cols="10">
            <v-form>
              <v-row justify="center">
                <!-- ชื่อ-สกุล-->
                <v-col cols="7">
                  <v-text-field
                    solo
                    label="ชื่อ-สกุล"
                    v-model="name"
                    :rules="[(v) => !!v || 'This field is required']"
                    required
                    clearable
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row justify="center">
                <!-- ตำแหน่ง -->
                <v-col cols="5">
                  <v-select
                    label="ตำแหน่ง"
                    solo
                    v-model="Employee.position"
                    :items="position"
                    item-text="position"
                    item-value="position_id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
                <!-- จังหวัดที่ตั่ง -->
                <v-col cols="4">
                  <v-select
                    label="จังหวัด"
                    solo
                    v-model="Employee.province"
                    :items="province"
                    item-text="province"
                    item-value="province_id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
              </v-row>
              <v-row justify="center">
                <!-- ประเภทโทรศัพท์ -->

                <v-col cols="3">
                  <v-select
                    label="ประเภทโทรศัพท์"
                    solo
                    v-model="Employee.phonetype"
                    :items="phonetype"
                    item-text="phonetype"
                    item-value="phonetype_id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
                <!--เบอร์โทรศัพท์-->
                <v-col cols="5">
                  <v-text-field
                    solo
                    label="เบอร์โทรศัพท์"
                    v-model="phone"
                    :rules="[(v) => !!v || 'This field is required']"
                    required
                    clearable
                  ></v-text-field>
                </v-col>
              </v-row>

              <!-- email -->
              <v-row justify="center">
                <v-col cols="8">
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

              <!-- password -->
              <v-row justify="center">
                <v-col cols="8">
                  <v-text-field
                    solo
                    label="PASSWORD"
                    v-model="password"
                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="[rules.required, rules.min]"
                    :type="show1 ? 'text' : 'password'"
                    hint="At least 8 characters"
                    prepend-icon="mdi-lock"
                    required
                    counter
                    clearable
                    @click:append="show1 = !show1"
                  ></v-text-field>
                </v-col>
              </v-row>

              <!-- ยืมยันpassword -->
              <v-row justify="center">
                <v-col cols="8">
                  <v-text-field
                    solo
                    label="ยืนยัน PASSWORD"
                    v-model="repassword"
                    :type="show2 ? 'text' : 'password'"
                    :append-icon="show2 ?  'mdi-eye' : 'mdi-eye-off'"
                    :rules="[rules.required,rules.checkpass]"
                    hint="At least 8 characters"
                    prepend-icon="mdi-lock"
                    required
                    counter
                    clearable
                    @click:append="show2 = !show2"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row justify="center" style="height: 20px;">     
              <v-btn  @click="saveData">save</v-btn>  
              </v-row>
            
              <div v-if="alert === 'null'">
              </div>
              <div v-else-if="alert === 'true'">
                <v-alert type="success">บันทึกสำเร็จ</v-alert>
              </div>
              <div v-else-if="alert === 'false'">
                <v-alert type="error">บันทึกไม่สำเร็จ</v-alert>
              </div> 
            </v-form>
          </v-col>
        </v-row>
        <br />
    <v-col cols="3">
      <v-btn x-medium color="#6C7B8B" style="margin-left: 380%;" dark @click="back">Back</v-btn>
    </v-col>
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-system-bar color="#CD919E"></v-system-bar>
      </v-card>
    </v-navigation-drawer>
  </v-card>
</template>
<script>
import http from "../http-common";

export default {
  name: "Employee",
  data() {
    return {
      Employee: {
        phonetype: null,
        position: null,
        province: null
      },
      show1: false,
      show2: false,
      name: "",
      email: "",
      phone: "",
      password: "",
      repassword: "",
      position: null,
      phonetype: null,
      province: null,
      alert: "null",

      rules: {
        required: value => !!value || "This field is required",
        min: v => v.length >= 8 || "Min 8 characters",
        checkpass: () =>
          this.repassword == this.password || "Passwords do not match"
      }
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล position ใส่ combobox
    getposition() {
      http
        .get("/position")
        .then(response => {
          this.position = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล phonetype ใส่ combobox
    getphonetype() {
      http
        .get("/phonetype")
        .then(response => {
          this.phonetype = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล province ใส่ combobox
    getProvince() {
      http
        .get("/province")
        .then(response => {
          this.province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveData() {
      if (
        this.password == this.repassword &&
        this.password.length >= 8 &&
        this.repassword.length >= 8
      ) {
        http
          .post(
            "/Employee/" +
              this.name +
              "/" +
              this.email +
              "/" +
              this.password +
              "/" +
              this.phone +
              "/" +
              this.Employee.position +
              "/" +
              this.Employee.phonetype +
              "/" +
              this.Employee.province
          
        ).then(response => {
          console.log(response);
          this.alert = "true";  
        })
        .catch(e => {
          console.log(e);
          this.alert = "false";

        });
      
    }else{
          this.alert = "false";
    }
    },
 back() {
      this.$router.push("/Adminmenu");
    }
  },
  mounted() {
    this.getposition();
    this.getphonetype();
    this.getProvince();
  }
};
</script>
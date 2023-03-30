import { View, Text,Pressable, StyleSheet,TextInput } from 'react-native'
import React,{useState} from 'react'
import styles from './styles';
import { addToken } from '../../redux/features/tokenSlice';
import {useNavigation} from "@react-navigation/native";
import { RootStackParamList } from '../../navigation';
import { StackNavigationProp } from '@react-navigation/stack';
import axios from "axios";
// @ts-ignore
import {API_URL} from "@env";
import { useAppDispatch } from '../../redux/features/hooks';
import { User } from '../../types/types';
import { enterDetails } from '../../redux/features/userDetailsSlice';
export default function Login(): JSX.Element {
  const dispatch = useAppDispatch();
  const [email,setEmail] = useState<string>("");
  const [pwd,setPwd] = useState<string>("");

  const navigation = useNavigation<StackNavigationProp<RootStackParamList>>();
  const headers = {
    'Content-Type': 'application/json',
  };
  const handleLogin = (): void =>{
    console.log(API_URL)
   console.log(pwd + " " + email)
  axios.post(`${API_URL}/api/auth/authenticate`,{email: email,password: pwd}, { headers }).then((res)=>
  {
    dispatch(addToken(res.data.token))
    const {id,name,email,password,about} :User = res.data.user;
    dispatch(enterDetails({id:id,name:name,email:email,password:password,about:about}))
    navigation.navigate('BottomTabs')
  }
    ).catch(e=>console.log(e));
  }


 

  return (
    <View style={styles.container}>
      <View></View>
      <View style={styles.loginContainer}>

    <Text style={styles.title}>Welcome to the Blog App!</Text>
    <TextInput
      style={styles.input}
      placeholder="Email"
      value={email}
      onChangeText={(text: string) => setEmail(text)}
      />
    <TextInput
      style={styles.input}
      placeholder="Password"
      secureTextEntry={true}
      value={pwd}
      onChangeText={(text: string) => setPwd(text)}
      />
    <Pressable style={styles.button} onPress={handleLogin}>
      <Text style={styles.buttonText}>Login</Text>
    </Pressable>
      </View>
    <View style={styles.signUpContainer}>
      <Text>Don't have an account ? </Text>
      <Pressable onPress={()=>navigation.navigate("Signup")}>
        <Text style={styles.linkText}>Signup</Text>
      </Pressable>
    </View>
  </View>
  )
}

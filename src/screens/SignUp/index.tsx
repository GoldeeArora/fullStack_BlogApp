import { useNavigation } from '@react-navigation/native';
import { StackNavigationProp } from '@react-navigation/stack';
import React, { useState } from 'react';
import { View, Text, TextInput, Pressable } from 'react-native';
import { RootStackParamList } from '../../navigation';
import {SignupForm} from "../../types/types";
import styles from './styles';
const Signup: React.FC = () => {
  const navigation = useNavigation<StackNavigationProp<RootStackParamList>>();
  const [formData, setFormData] = useState<SignupForm>({
    name: '',
    email: '',
    password: '',
    about: '',
  });

  const handleFormChange = (key: keyof SignupForm, value: string) => {
    setFormData({ ...formData, [key]: value });
  };

  const handleSignup = () => {
    // Handle signup logic here
  };

  return (
    <View style={styles.container}>
      <View></View>
    <View style={styles.signUpContainer}>
      <Text style={styles.title}>Sign Up</Text>
      <TextInput
        style={styles.input}
        placeholder="Name"
        onChangeText={(text) => handleFormChange('name', text)}
        value={formData.name}
      />
      <TextInput
        style={styles.input}
        placeholder="Email"
        onChangeText={(text) => handleFormChange('email', text)}
        value={formData.email}
      />
      <TextInput
        style={styles.input}
        placeholder="Password"
        secureTextEntry
        onChangeText={(text) => handleFormChange('password', text)}
        value={formData.password}
      />
      <TextInput
        style={styles.input}
        placeholder="About"
        onChangeText={(text) => handleFormChange('about', text)}
        value={formData.about}
      />
      <Pressable style={styles.button} onPress={handleSignup}>
        <Text style={styles.buttonText}>Sign Up</Text>
      </Pressable>
      </View>
      <View style={styles.loginContainer}>
        <Text>Already have an account? 
          </Text>
          <Pressable onPress={()=>navigation.navigate("Login")}>
              <Text style={styles.loginText}>
              Login
                </Text>
          </Pressable>
      </View>
    </View>
  );
};


export default Signup
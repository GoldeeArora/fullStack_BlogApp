import { View, Text } from 'react-native'
import React, { JSXElementConstructor } from 'react'
import Login from "./screens/Login"
import SignUp from './screens/SignUp'
import {NavigationContainer} from "@react-navigation/native"
import Navigation from './navigation'
import { Provider } from 'react-redux'
import { store } from './redux/store'
export default function App(): JSX.Element{
  return (
   <Provider store={store}>

 <Navigation />
   </Provider>

  )
}
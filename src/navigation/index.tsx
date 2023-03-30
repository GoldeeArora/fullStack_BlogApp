import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import Ionicons from 'react-native-vector-icons/Ionicons';
import HomeScreen from '../screens/HomeScreen';
import SearchScreen from '../screens/SearchScreen';
import MyBlogsScreen from '../screens/MyBlogsScreen';
import ProfileScreen from '../screens/ProfileScreen';
import Login from '../screens/Login';
import Signup from '../screens/SignUp';
import { COLORS } from '../styles/colors';
import { createStackNavigator } from '@react-navigation/stack';
import PostScreen from '../screens/PostScreen';
import PostsByCategory from '../screens/PostsByCategory';
export type RootStackParamList = {
  Login: undefined,
  Signup: undefined,
  BottomTabs: undefined,
  Post: {
    id: string|number,
    imageSource: string,
          title: string,
          date: string|Date,
          content: string
}
PostsByCategory:{
  id: number|string
}
};
type BottomParamList = {
  Home: undefined,
  Search: undefined,
  MyBlogs: undefined,
  Profile: undefined
}
const Tab = createBottomTabNavigator<BottomParamList>();
const Stack = createStackNavigator<RootStackParamList>();
const Navigation = () => {
  const renderTabIcon = (
    route: any,
    focused: boolean,
    color: string,
    size: number,
  ) => {
    let iconName = 'home';
    switch (route.name) {
      case "home":
        iconName = focused ? 'home' : 'home-outline';
        break;
      case "MyBlogs":
        iconName = focused ? 'book' : 'book-outline';
        break;
      case "Profile":
        iconName = focused ? 'settings' : 'settings-outline';
        break;
      case "Search":
        iconName = focused ? 'search' : 'search-outline';
        break;
      default:
        iconName = focused ? 'home' : 'home-outline';
        break;
    }
    return <Ionicons name={iconName} size={size} color={color} />;
  };
  const BottomTabs = (): JSX.Element=>{
    return(
<Tab.Navigator
        screenOptions={({route}) => ({
          // headerShown: false,
          tabBarHideOnKeyboard: true,
          tabBarIcon: ({focused, color, size}) =>
            renderTabIcon(route, focused, color, size),
          tabBarActiveTintColor: COLORS.appColor,
          tabBarInactiveTintColor: COLORS.gray,
          tabBarStyle: {
            backgroundColor: COLORS.white,
          },
        })}>
        <Tab.Screen name={"Home"} component={HomeScreen} />
        <Tab.Screen name={"Search"} component={SearchScreen} />
        <Tab.Screen name={"MyBlogs"} component={MyBlogsScreen} />
        <Tab.Screen name={"Profile"} component={ProfileScreen} />
      </Tab.Navigator>
    )

  }
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{headerShown: false}}>
            <Stack.Screen name="Login" component={Login} />
            <Stack.Screen name="Signup" component={Signup} />
            <Stack.Screen name="Post" component={PostScreen}  initialParams={{ id:'notDefined',title:'',imageSource:'',date:'',content:'' }}/>

            <Stack.Screen name="PostsByCategory" component={PostsByCategory} initialParams={{id:''}} />
            <Stack.Screen name="BottomTabs" component={BottomTabs} />
          </Stack.Navigator>
    </NavigationContainer>
  );
};

export default Navigation;
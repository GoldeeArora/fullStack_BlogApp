import { View, Text } from 'react-native'
import React,{useState} from 'react'
import { useRoute } from '@react-navigation/native'
import { PostType } from '../../types/types';
export default function PostScreen() {

  const route = useRoute();
  const postInfo = route.params as PostType | undefined
  
  return (
    <View>
     <Text>{postInfo && postInfo.title}</Text>
    </View>
  )
}
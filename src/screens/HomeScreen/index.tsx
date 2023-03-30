import { View, Text,Pressable, FlatList } from 'react-native'
import React, { useEffect,useState } from 'react'
import {useNavigation} from "@react-navigation/native"
import { StackNavigationProp } from '@react-navigation/stack/lib/typescript/src/types';
import { RootStackParamList } from '../../navigation';
import Post from '../../components/Post';
import {PostType} from "../../types/types"
// @ts-ignore
import {API_URL} from "@env"
import { useAppSelector } from '../../redux/features/hooks';
import { selectToken } from '../../redux/features/tokenSlice';
import axios from 'axios';

export default function HomeScreen(): JSX.Element {
  const [posts,setPosts] = useState<PostType[]>();
  const getToken = useAppSelector(selectToken);
  
  const getAllPosts = ()=>{
    axios.get(`${API_URL}/api/posts`,{
      headers: {
        Authorization: `Bearer ${getToken.token}`
      }
    }).then(res=>
      {
        setPosts(res.data.content);
      }).catch(e=>console.log(e));
  }
  useEffect(()=>{
    getAllPosts()
  },[])
 
  return (
    <FlatList 
    data={posts}
    renderItem={({item}: {item: PostType}):JSX.Element=> 
    (
      <View>
    <Post id={item.postId} imageSource={item.imageName} date={item.addedDate} title={item.title} content={item.content}/>
        </View>
    )
  }
  keyExtractor={(item:PostType,index:number):string=>index.toString()}
  showsHorizontalScrollIndicator={false}
  showsVerticalScrollIndicator={false}

  />
  )
}
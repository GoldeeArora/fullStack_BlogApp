import { createSlice } from '@reduxjs/toolkit'
import type { PayloadAction } from '@reduxjs/toolkit'
import type { RootState } from '../store'

// Define a type for the slice state
interface tokenState {
  token: string
}

// Define the initial state using that type
const initialState: tokenState = {
  token: '',
}

export const tokenSlice = createSlice({
  name: 'token',
  // `createSlice` will infer the state type from the `initialState` argument
  initialState,
  reducers: {
    // Use the PayloadAction type to declare the contents of `action.payload`
    addToken: (state, action: PayloadAction<string>) => {
      state.token = action.payload
    },
  },
})

export const { addToken } = tokenSlice.actions

// Other code such as selectors can use the imported `RootState` type
export const selectToken = (state: RootState) => state.token;

export default tokenSlice.reducer
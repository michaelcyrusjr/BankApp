const API_URL = 'http://localhost:8080/api/accounts'

export async function getAccounts() {
  const response = await fetch(API_URL)

  if (!response.ok) {
    throw new Error('Failed to load accounts')
  }

  return response.json()
}

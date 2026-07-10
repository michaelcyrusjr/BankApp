import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CListGroup,
  CListGroupItem,
  CRow,
} from '@coreui/react'

import CIcon from '@coreui/icons-react'
import {
  cilSwapHorizontal,
  cilDollar,
  cilCloudUpload,
  cilDescription,
  cilCreditCard,
  cilArrowTop,
  cilArrowBottom,
  cilWallet,
  cilAirplaneMode,
  cilHome,
} from '@coreui/icons'

import SummaryCard from '../../components/SummaryCard'
import AccountRow from '../../components/AccountRow'
import TransactionRow from '../../components/TransactionRow'
import { useEffect, useState } from 'react'
import { getAccounts } from '../../api/accountsApi'
import AccountCard from '../../components/AccountCard'

const Dashboard = () => {
  const [accounts, setAccounts] = useState([])
  const [loading, setLoading] = useState(true)
  console.log('render accounts:', accounts)

  useEffect(() => {
    const loadAccounts = async () => {
      try {
        const data = await getAccounts()
        console.log('fetched accounts:', data)
        setAccounts(data)
      } catch (error) {
        console.error('Failed to load accounts:', error)
      } finally {
        setLoading(false)
      }
    }

    loadAccounts()
  }, [])

  useEffect(() => {
    console.log('accounts changed:', accounts)
  }, [accounts])

  const totalBalance = '$64,046.00'
  const totalAccounts = accounts.length
  const totalIncome = '$4,850'
  const totalExpenses = '$2,310.00'
  const recentActivity = '5'

  const summaryCard = [
    {
      title: 'Total Balance',
      icon: cilDollar,
      color: 'success',
      value: totalBalance,
      subtitle: '+ 2.45% from last month',
    },
    {
      title: 'Total Accounts',
      icon: cilCreditCard,
      color: 'primary',
      value: totalAccounts,
      subtitle: 'Total Accounts',
    },
    {
      title: 'Total Income',
      icon: cilArrowBottom,
      color: 'success',
      value: totalIncome,
      subtitle: 'This Month',
    },
    {
      title: 'Total Expenses',
      icon: cilArrowTop,
      color: 'danger',
      value: totalExpenses,
      subtitle: 'This Month',
    },
  ]

  const transactions = [
    { name: 'Salary Deposit', date: 'May 24, 2026', amount: '+$3,200.00' },
    { name: 'Electric Bill', date: 'May 22, 2026', amount: '-120.00' },
    { name: 'Groceries', date: 'May 21, 2026', amount: '-$86.44' },
  ]

  const mockAccounts = [
    {
      id: 1,
      name: 'Checking Account',
      accountNumber: '1234567890',
      balance: '$12,540.00',
      balanceLabel: 'Available',
      icon: cilCreditCard,
      color: 'success',
    },
    {
      id: 2,
      name: 'Savings Account',
      accountNumber: '9876543210',
      balance: '$10,250.00',
      balanceLabel: 'Available',
      icon: cilDollar,
      color: 'primary',
    },
    {
      id: 3,
      name: 'Emergency Fund',
      accountNumber: '4567891230',
      balance: '$8,750.00',
      balanceLabel: 'Reserved',
      icon: cilWallet,
      color: 'warning',
    },
    {
      id: 4,
      name: 'Travel Fund',
      accountNumber: '7891234560',
      balance: '$2,300.00',
      balanceLabel: 'Available',
      icon: cilAirplaneMode,
      color: 'info',
    },
    {
      id: 5,
      name: 'Bills Account',
      accountNumber: '3216549870',
      balance: '$925.50',
      balanceLabel: 'Upcoming',
      icon: cilHome,
      color: 'danger',
    },
  ]

  const displayedAccounts = accounts.length > 0 ? accounts : mockAccounts

  const quickActions = [
    {
      label: 'Transfer Money',
      icon: cilSwapHorizontal,
      color: 'success',
    },
    {
      label: 'Pay Bills',
      icon: cilDollar,
      color: 'info',
    },
    {
      label: 'Deposit Check',
      icon: cilCloudUpload,
      color: 'primary',
    },
    {
      label: 'View Statements',
      icon: cilDescription,
      color: 'warning',
    },
  ]

  return (
    <>
      <CRow className="mb-4">
        <CCol xs={12}>
          <h1 className="mb-1">Welcome back, Michael!</h1>
          <p className="text-medium-emphasis mb-0">Here's an overview of your accounts today.</p>
        </CCol>
      </CRow>

      <CRow className="g-4 mb-4">
        <div className="summary-card-grid">
          {summaryCard.map((card) => (
            <SummaryCard
              key={card.title}
              title={card.title}
              value={card.value}
              icon={card.icon}
              color={card.color}
              subtitle={card.subtitle}
            />
          ))}
        </div>
      </CRow>

      <CRow className="g-4">
        <CCol xl={6}>
          <CCard className="dashboard-list-card">
            <CCardHeader className="d-flex justify-content-between align-items-center">
              <strong>Accounts Overview</strong>
              <CButton color="primary" size="sm">
                Transfer Money
              </CButton>
            </CCardHeader>

            <CCardBody>
              <div className="accountsrow-grid">
                {displayedAccounts.map((account) => (
                  <AccountCard
                    key={account.id}
                    accountName={account.name || 'Account'}
                    accountNumber={account.accountNumber}
                    balance={account.balance}
                    balanceLabel={account.balanceLabel}
                    icon={account.icon}
                    color={account.color || 'primary'}
                  />
                ))}
              </div>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol xl={6}>
          <CCard className="dashboard-list-card">
            <CCardHeader className="d-flex justify-content-between align-items-center">
              <strong>Recent Transactions</strong>
              <CButton color="link" size="sm" className="text-decoration-none p-0">
                View All
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CListGroup flush>
                {transactions.map((transaction) => (
                  <TransactionRow
                    key={transaction.name}
                    name={transaction.name}
                    date={transaction.date}
                    amount={transaction.amount}
                  />
                ))}
              </CListGroup>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol xs={12}>
          <CCard className="mt-1 quick-actions-card">
            <CCardBody>
              <strong className="quick-actions-title">Quick Actions</strong>

              <div className="quick-actions-grid">
                {quickActions.map((action) => (
                  <CButton
                    key={action.label}
                    color={action.color}
                    variant="outline"
                    className="quick-action-button"
                  >
                    <span className={`quick-action-icon text-${action.color}`}>
                      <CIcon icon={action.icon} size="lg" />
                    </span>

                    <span>{action.label}</span>
                  </CButton>
                ))}
              </div>
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
    </>
  )
}

export default Dashboard
